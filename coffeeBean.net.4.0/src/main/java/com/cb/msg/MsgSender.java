package com.cb.msg;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.cache.ChannelCache;
import com.cb.coder.MsgEncoder;
import com.cb.util.DeviceType;
import com.google.protobuf.GeneratedMessageV3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MsgSender {
	private static final ThreadLocal<Channel> threadChannel = new ThreadLocal<Channel>();
	
	private static final Logger logger = LoggerFactory.getLogger(MsgSender.class);
	
	public static void setCurrChannel(Channel channel) {
		threadChannel.set(channel);
	}

	public static Channel getCurrChannel() {
		return threadChannel.get();
	}
	
	public static boolean sendMsg(Message message) {
		Channel channel = getCurrChannel();
		return sendMsg(message, channel);
	}
	
	public static boolean sendMsg(PbMessage<? extends GeneratedMessageV3> message) {
		Channel channel = getCurrChannel();
		return sendMsg(message, channel);
	}
	
	public static boolean sendMsg(final Message message, long accountId) {
		message.setAccountId(accountId);
		Channel channel = ChannelCache.getChannel(accountId);
		if (null == channel || !channel.isOpen() || !channel.isActive()) {
			return false;
		} else {
			return sendMsg(message, channel);
		}
	}
	
	public static boolean sendMsg(final PbMessage<? extends GeneratedMessageV3> message, long accountId) {
		message.setAccountId(accountId);
		Channel channel = ChannelCache.getChannel(accountId);
		if (null == channel || !channel.isOpen() || !channel.isActive()) {
			return false;
		} else {
			return sendMsg(message, channel);
		}
	}
	
	public static void sendBroadcast(Message message, List<Long> roleIdList) {
		if (CollectionUtils.isNotEmpty(roleIdList)) {
			ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
			roleIdList.forEach(accountId -> {
				Channel channel = ChannelCache.getChannel(accountId);
				if (channel != null && channel.isOpen() && channel.isActive()) {
					channelGroup.add(channel);
				}
			});
			if (channelGroup.size() > 0) {
				ChannelGroupFuture cgf = channelGroup.writeAndFlush(message);
				cgf.forEach(action -> action.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture f) throws Exception {
						printChannelFutureInfo(f, message);
					}
				}));
			}
		}
	}
	
	public static void sendBroadcast(final PbMessage<? extends GeneratedMessageV3> msg, List<Long> roleIdList) {
		if (CollectionUtils.isNotEmpty(roleIdList)) {
			ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
			roleIdList.forEach(accountId -> {
				Channel channel = ChannelCache.getChannel(accountId);
				if (channel != null && channel.isOpen() && channel.isActive()) {
					channelGroup.add(channel);
				}
			});
			if (channelGroup.size() > 0) {
				Message message = pb2cb(msg);
				ChannelGroupFuture cgf = channelGroup.writeAndFlush(message);
				cgf.forEach(action -> action.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture f) throws Exception {
						printChannelFutureInfo(f, message);
					}
				}));
			}
		}
	}
	
	public static boolean sendMsg(final Message message, Channel channel) {
		if (null != channel) {
			ChannelFuture future = null;
			if (message.getDeviceType() == DeviceType.Browser) {
				ByteBuf cloneBuffer = MsgEncoder.doEncode(message);
				future = channel.writeAndFlush(new BinaryWebSocketFrame(cloneBuffer));
			} else {
				future = channel.writeAndFlush(message);
			}
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture f) throws Exception {
					printChannelFutureInfo(f, message);
				}
			});
		    return true;
		} else {
			return false;
		}
	}
	
	public static boolean sendMsg(final PbMessage<? extends GeneratedMessageV3> msg, Channel channel) {
		if (null != channel) {
			ChannelFuture future = null;
			Message message = pb2cb(msg);
			if (msg.getDeviceType() == DeviceType.Browser) {
				ByteBuf cloneBuffer = MsgEncoder.doEncode(message);
				future = channel.writeAndFlush(new BinaryWebSocketFrame(cloneBuffer));
			} else {
				future = channel.writeAndFlush(message);
			}
			future.addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture f) throws Exception {
					printChannelFutureInfo(f, message);
				}
			});
		    return true;
		} else {
			return false;
		}
	}
	
	private static Message pb2cb(final PbMessage<? extends GeneratedMessageV3> msg) {
		Message message = new Message();
		message.setAccountId(msg.getAccountId());
		message.setMsgcd(msg.getMsgcd());
		message.setErrorcd(msg.getErrorCode());
		message.setErrorInfo(msg.getErrorInfo());
		Class<?> bodyClazz = msg.getBody().getClass();
		try {
			Method toByteArrayMethod = bodyClazz.getMethod("toByteArray");
			byte[] bytes = (byte[])toByteArrayMethod.invoke(msg.getBody());
			ByteBuf buffer = Unpooled.copiedBuffer(bytes);
			message.setBodyLen((short)bytes.length);
			message.buffer = buffer;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return message;
	}
	
	private static void printChannelFutureInfo(ChannelFuture f, Message message) {
		if (f.isSuccess()) {
			logger.info(String.format("SEND %d to accountId=%d", message.getMsgcd(), message.getAccountId()));
		} else {
			StringWriter sw = new StringWriter();
			f.cause().printStackTrace(new PrintWriter(sw));
			String errorContent = sw.toString();
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("SEND %d to accountId=%d fail", message.getMsgcd(), message.getAccountId()));
			sb.append("\n");
			sb.append(errorContent);
			logger.error(sb.toString());
		}
	}
	
	public static void printException(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String message = sw.toString();
		logger.error(message);
	}
	
}

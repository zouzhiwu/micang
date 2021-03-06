package com.cb.msg;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.cache.AddressCache;
import com.cb.cache.ChannelCache;
import com.cb.cache.UdpChannelCache;
import com.cb.coder.MsgEncoder;
import com.cb.util.Constant;
import com.cb.util.DeviceType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MsgSender {
	private static final ThreadLocal<Long> threadChannel = new ThreadLocal<Long>();
	
	private static final Logger logger = LoggerFactory.getLogger(MsgSender.class);
	
	public static void setMemberId(Long accountId) {
		threadChannel.set(accountId);
	}

	public static Long getMemberId() {
		return threadChannel.get();
	}
	
	public static boolean sendMsg(Message message) {
		Long accountId = getMemberId();
		return sendMsg(message, accountId);
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
						if (f.isSuccess()) {
							logger.info(String.format("SEND %d to accountId=%d", message.getMsgcd(), action.channel().attr(Constant.accountId).get()));
						} else {
							StringWriter sw = new StringWriter();
							f.cause().printStackTrace(new PrintWriter(sw));
							String errorContent = sw.toString();
							StringBuilder sb = new StringBuilder();
							sb.append(String.format("SEND %d to accountId=%d fail", message.getMsgcd(), action.channel().attr(Constant.accountId).get()));
							sb.append("\n");
							sb.append(errorContent);
							logger.error(sb.toString());
						}
					}
				}));
			}
		}
	}
	
	public static boolean sendMsg(final Message message, Channel channel) {
		// 如果登录成功，则保存令牌
		if (0x0201 == message.getMsgcd()) {
			int rtn = message.getShort();
			if (0 == rtn) {
				message.resetReaderIndex();
			}
		}
		if (null != channel) {
			Boolean isLoading = channel.attr(Constant.isLoading).get();
			// 如果客户端是不在加载房间场景，或心跳消息或大厅快照消息，则发送，其他消息忽略发送
			if (!Boolean.TRUE.equals(isLoading) || message.getMsgcd() == 1003 || message.getMsgcd() == 2038) {
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
				});
			} else {
				logger.warn(String.format("SEND %d to accountId=%d ignore", message.getMsgcd(), message.getAccountId()));
			}
		    return true;
		} else {
			return false;
		}
	}
	
	public static boolean sendUdpMsg(final Message message) {
		Long accountId = getMemberId();
		return sendUdpMsg(message, accountId);
	}
	
	public static boolean sendUdpMsg(final Message message, Long accountId) {
		if (accountId != null) {
			Channel channel = getUdpChannel(accountId);
			InetSocketAddress inetSocketAddress = getAddress(accountId);
			return sendUdpMsg(message, channel, inetSocketAddress);
		} else {
			return false;
		}
	}
	
	public static boolean sendUdpMsg(final Message message, Channel channel, InetSocketAddress inetSocketAddress) {
		// 如果登录成功，则保存令牌
		if (0x0201 == message.getMsgcd()) {
			int rtn = message.getShort();
			if (0 == rtn) {
				message.resetReaderIndex();
			}
		}
		if (null != inetSocketAddress && null != channel) {
				ChannelFuture future = null;
				ByteBuf cloneBuffer = MsgEncoder.doEncode(message);
				DatagramPacket data = new DatagramPacket(cloneBuffer, inetSocketAddress);
				future = channel.writeAndFlush(data);
				future.addListener(new ChannelFutureListener() {
					@Override
					public void operationComplete(ChannelFuture f) throws Exception {
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
				});
		    return true;
		} else {
			return false;
		}
	}
	
	public static void printException(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		String message = sw.toString();
		logger.error(message);
	}
	
	public static boolean hasChannel(long accountId) {
		return ChannelCache.hasChannel(accountId);
	}
	
	public static Channel getChannel(long key) {
		return ChannelCache.getChannel(key);
	}
	
	public static Channel removeChannel(long accountId) {
		return ChannelCache.removeChannel(accountId);
	}
	
	public static Channel putChannel(long key, Channel channel) {
		return ChannelCache.putChannel(key, channel);
	}
	
	public static boolean hasUdpChannel(long accountId) {
		return UdpChannelCache.hasChannel(accountId);
	}
	
	public static Channel getUdpChannel(long key) {
		return UdpChannelCache.getChannel(key);
	}
	
	public static Channel removeUdpChannel(long accountId) {
		return UdpChannelCache.removeChannel(accountId);
	}
	
	public static Channel putUdpChannel(long key, Channel channel) {
		return UdpChannelCache.putChannel(key, channel);
	}
	
	public static boolean hasAddress(long accountId) {
		return AddressCache.hasAddress(accountId);
	}
	
	public static InetSocketAddress getAddress(long key) {
		return AddressCache.getAddress(key);
	}
	
	public static InetSocketAddress removeAddress(long accountId) {
		return AddressCache.removeAddress(accountId);
	}
	
	public static InetSocketAddress putAddress(long key, InetSocketAddress inetSocketAddress) {
		return AddressCache.putAddress(key, inetSocketAddress);
	}
}

package com.cb.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cb.action.ActionMethodClazz;
import com.cb.action.MethodClazz;
import com.cb.cache.ChannelCache;
import com.cb.exception.ErrorException;
import com.cb.exception.InfoException;
import com.cb.lisener.AbsLisener;
import com.cb.msg.Message;
import com.cb.msg.MsgSender;
import com.cb.msg.PbMessage;
import com.google.protobuf.GeneratedMessageV3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable
public class MsgHandler extends ChannelInboundHandlerAdapter {
private static final Logger logger = LoggerFactory.getLogger(MsgHandler.class);
	
	private AbsLisener listener;
	private short loginMsgcd;
	
	public MsgHandler(AbsLisener listener) {
		this.listener = listener;
		loginMsgcd = this.listener.getLoginMsgcd();
	}
	
	@Override
	public void channelRead(final ChannelHandlerContext ctx, final Object obj) throws Exception {
		if (obj instanceof Message) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					Message message = (Message) obj;
					MethodClazz cbMethodClazz = listener.cbActionMap.get(message.getMsgcd());
					Channel channel = ctx.channel();
					MsgSender.setCurrChannel(channel);
					if (null != cbMethodClazz) {
						dispenserCbMsg(cbMethodClazz, message, channel);
					} else {
						ActionMethodClazz pbMethodClazz = listener.pbActionMap.get(message.getMsgcd());
						if (null != pbMethodClazz) {
							dispenserPbMsg(pbMethodClazz, message, channel);
						} else {
							logger.error(String.format("没有对应的指令%d", message.getMsgcd()));
						}
					}
				}
			}).start();
		}
	}
	
	 private void dispenserCbMsg(MethodClazz methodClazz, Message message, Channel channel) {
		boolean isHasChannel;
		if (message.getMsgcd() == loginMsgcd) {
			isHasChannel = true;
		} else {
			isHasChannel = ChannelCache.hasChannel(message.getAccountId());
		}
		if (isHasChannel) {
			try {
				Method method = methodClazz.getMethod();
				Object instance = methodClazz.getInstance();
				method.invoke(instance, message, channel);
			} catch (InvocationTargetException exception) {
				Throwable throwable = exception.getTargetException();
				if (throwable instanceof InfoException) {
					InfoException ex = (InfoException)throwable;
					logger.info(String.format("msgcd=%d from roleId=%d infomessage:%s", message.getMsgcd(), message.getAccountId(), ex.getClientInfo()));
					printInfo(exception);
					try {
						Message rtn = new Message(message);
						rtn.setMsgcd(message.getMsgcd());
						rtn.setErrorcd(ex.getErrorCode());
						rtn.setErrorInfo(ex.getClientInfo());
						MsgSender.sendMsg(rtn, channel);
					} catch (Exception e) {
						printInfo(e);
					}
				} else if (throwable instanceof ErrorException) {
					printInfo(exception);
				} else {
					printInfo(exception);
				}
			} catch (Exception exception) {
				printInfo(exception);
			}
		} else {
			logger.error(String.format("msgcd=%d, accountId=%s, 没找到对应的通道", message.getMsgcd(), message.getAccountId()));
		}
	 }
	 
	 private void dispenserPbMsg(ActionMethodClazz pbMethodClazz, Message message, Channel channel) {
			boolean isHasChannel;
			if (message.getMsgcd() == loginMsgcd) {
				isHasChannel = true;
			} else {
				isHasChannel = ChannelCache.hasChannel(message.getAccountId());
			}
			if (isHasChannel) {
				try {
					Class<?> pbClazz = pbMethodClazz.getPbClazz();
					Method actionMethod = pbMethodClazz.getActionMethod();
					Object actionBean = pbMethodClazz.getActionBean();
					Method parseFromMethod = pbClazz.getMethod("parseFrom", byte[].class);
					byte[] bytes = message.getBody().array();
					Object instance = parseFromMethod.invoke(null, bytes);
					PbMessage<GeneratedMessageV3> msg = new PbMessage<GeneratedMessageV3>();
					msg.setMsgcd(message.getMsgcd());
					msg.setBody((GeneratedMessageV3)instance);
					actionMethod.invoke(actionBean, msg);
				} catch (InvocationTargetException exception) {
					Throwable throwable = exception.getTargetException();
					if (throwable instanceof InfoException) {
						InfoException ex = (InfoException) throwable;
						logger.info(String.format("msgcd=%d from roleId=%d infomessage:%s"
								, message.getMsgcd(), message.getAccountId(), ex.getClientInfo()));
						printInfo(exception);
						try {
							PbMessage<GeneratedMessageV3> rtn = new PbMessage<GeneratedMessageV3>();
							rtn.setMsgcd(message.getMsgcd());
							rtn.setErrorCode(ex.getErrorCode());
							rtn.setErrorInfo(ex.getClientInfo());
							MsgSender.sendMsg(rtn, channel);
						} catch (Exception e) {
							printInfo(e);
						}
					} else if (throwable instanceof ErrorException) {
						printInfo(exception);
					} else {
						printInfo(exception);
					}
				} catch (Exception exception) {
					printInfo(exception);
				}
			} else {
				logger.error(String.format("msgcd=%d, accountId=%s, 没找到对应的通道", message.getMsgcd(), message.getAccountId()));
			}
		 }
	
	public static void printInfo(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String message = sw.toString();
		if (e instanceof InfoException) {
			logger.warn(message);
		} else {
			logger.error(message);
		}
	}
	
	@Override 
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.info(cause.toString());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		this.listener.channelInactive(ctx.channel());
	}
}

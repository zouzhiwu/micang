package com.cb.coder;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.List;

import com.cb.msg.Message;
import com.cb.util.ChannelUtil;
import com.cb.util.DeviceType;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author Administrator
 *
 */
public class MsgDecoder extends ByteToMessageDecoder {
	protected final static Charset charset = Charset.forName("UTF-8");
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buffer, List<Object> out) throws Exception {
		buffer = buffer.order(ByteOrder.LITTLE_ENDIAN);
		if (buffer.readableBytes() < 2) {
			return;
		}
		buffer.markReaderIndex();
		short length = buffer.readShort();
		if (length > Short.MAX_VALUE) { 
			ctx.close();
			return;
		}
        if (buffer.readableBytes() < length) { 
        	buffer.resetReaderIndex();
            return;
        }
        Message message = doDecode(ctx.channel(), length, buffer);
        out.add(message);
	}
	
	public static Message doDecode(Channel channel, short length, ByteBuf buffer) {
		short msgcd = buffer.readShort();
		byte[] byteBody = new byte[length - 2];
		buffer.readBytes(byteBody);
		Message message = new Message();
		message.setMsgcd(msgcd);
		Long accountId = ChannelUtil.getAccountId(channel);
		if (accountId != null) {
			message.setAccountId(accountId);
		}
		Integer token = ChannelUtil.getToken(channel);
		if (token != null) {
			message.setToken(token);
		}
		Byte byteDeviceType = ChannelUtil.getDeviceType(channel);
		if (byteDeviceType != null) {
	    	DeviceType deviceType = DeviceType.getDeviceType(byteDeviceType);
	    	message.setDeviceType(deviceType);
	    }
		message.putBodyByte(byteBody);
		return message;
	}

	
}

package com.cb.util;

import com.cb.cache.ChannelCache;

import io.netty.channel.Channel;

public class ChannelUtil {
	
	public static boolean setHeartbeatTime(Channel channel, int time) {
		if (channel != null) {
			channel.attr(Constant.heartbeatTime).set(time);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean setRoomId(long roleId, int roomId) {
		Channel channel = ChannelCache.getChannel(roleId);
		if (channel != null) {
			channel.attr(Constant.roomId).set(roomId);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean setRoomId(Channel channel, int roomId) {
		if (channel != null) {
			channel.attr(Constant.roomId).set(roomId);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean setDeviceType(Channel channel, Byte deviceType) {
		if (channel != null) {
			channel.attr(Constant.deviceType).set(deviceType);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean setGuildId(Channel channel, Integer guildId) {
		if (channel != null) {
			channel.attr(Constant.guildId).set(guildId);
			return true;
		} else {
			return false;
		}
	}
	
	public static Integer getHeartbeatTime(Channel channel) {
		if (channel != null) {
			return channel.attr(Constant.heartbeatTime).get();
		} else {
			return null;
		}
	}
	
	public static Integer getRoomId(Channel channel) {
		if (channel != null) {
			return channel.attr(Constant.roomId).get();
		} else {
			return null;
		}
	}
	
	public static Long getAccountId(Channel channel) {
		if (channel != null) {
			return channel.attr(Constant.accountId).get();
		} else {
			return null;
		}
	}
	
	public static Byte getDeviceType(Channel channel) {
		if (channel != null) {
			return channel.attr(Constant.deviceType).get();
		} else {
			return null;
		}
	}
	
	public static Integer getGuildId(Channel channel) {
		if (channel != null) {
			return channel.attr(Constant.guildId).get();
		} else {
			return null;
		}
	}
	
}

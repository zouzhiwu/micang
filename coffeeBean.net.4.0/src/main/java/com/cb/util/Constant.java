package com.cb.util;

import io.netty.util.AttributeKey;


public class Constant {
//	AttributeKey.valueOf(Constant.createTime)
	public final static AttributeKey<Integer> heartbeatTime = AttributeKey.valueOf("heartbeatTime"); 	// 创建会话时间
	public final static AttributeKey<Integer> roomId = AttributeKey.valueOf("roomId"); 	// 房间Id
	public final static AttributeKey<Byte> deviceType = AttributeKey.valueOf("deviceType"); 	// 设备类型
	public final static AttributeKey<Integer> guildId = AttributeKey.valueOf("guildId"); 	// 工会Id
	public final static AttributeKey<Long> accountId = AttributeKey.valueOf("accountId"); 	// 创建会话时间
	
	public final static String anonymous = "anonymous";
	
	public final static String status_wait = "0";
	public final static String status_chat = "1";
	
	public final static int role_type_customer = 1;		// 角色 1：客户 2：客服
	public final static int role_type_servicer = 2;		// 角色 1：客户 2：客服
	
	public final static int active_off = 0;
	public final static int active_on = 1;
	
}

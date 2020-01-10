package com.common.constant;

public interface WangyiConstant {
	/**appkey**/
	String appKey = "26256d506a47ab81894d8306973f08f1";
	/**app 密钥**/
	String appSecret = "96be41cbb274";
	/**盐**/
	String nonce = "999999999";
	/**注册云信id**/
	String WANGYI_REGISTER_URL = "https://api.netease.im/nimserver/user/create.action";
	/**跟新用户名片**/
	String WANGYI_UPDATE_YUN_URL = "";
	/**封禁云信id**/
	String WANGYI_FORBID_URL = "";
	/**解禁云信id**/
	String WANGYI_UNBLOCK_URL = "";
	/**创建房间**/
	String WANGYI_CREATE_ROOM_URL = "";
	/**查询房间信息**/
	String WANGYI_SEARCH_ROOM_URL = "";
	/**请求聊天室地址**/
	String WANGYI_ROOM_ADDR_URL = "";
	/**关闭聊天室**/
	String WANYI_ROOM_CLOSE_URL = "";
	/**建立好友关系**/
	String WANYI_FRIENDS_URL = "";
	/**修改账户token**/
	String WANYI_TOKEN_URL = "";
	/**修改云信id**/
	String WANYI_UPDATE_URL = "";
	/**发送点对点消息**/
	String WANYI_POINT_URL = "https://api.netease.im/nimserver/msg/sendMsg.action";
	/**广播消息**/
	String WANYI_WORLD_URL = "https://api.netease.im/nimserver/msg/broadcastMsg.action";
	/**查询点对点历史消息**/
	String WANYI_HISTARY_URL = "https://api.netease.im/nimserver/history/querySessionMsg.action";

}

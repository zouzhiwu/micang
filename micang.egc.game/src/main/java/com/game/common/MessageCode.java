package com.game.common;

public class MessageCode {
	// 账号模块
	public final static short msg_account_login = 1001; // 登录服务器
	public final static short msg_account_heartbeat = 1002; // 心跳请求
	public final static short msg_account_logout = 1003; // 下线通知
	public final static short msg_account_online_list = 1004; // 获取在线玩家列表
	
	// 队员模块
	public final static short msg_member_list = 1101;	// 获取队员列表
	public final static short msg_member_recruit = 1102;	// 添加队员
	public final static short msg_member_set_goto_work = 1103;	// 设置队员上阵
	public final static short msg_member_update_lv = 1104;	// 队员升级LV
	public final static short msg_member_update_duan = 1105;	// 队员升级段位
	public final static short msg_member_expPot = 1106;	// 获取经验罐数值
	public final static short msg_member_update_star = 1107;	// 队员升星
	public final static short msg_member_add_hero = 1108;	// 队员熟练度面板添加英雄上阵
	public final static short msg_member_character = 1109;	// 刷新队员性格
	public final static short msg_member_character_update = 1110; //更新队员性格
	public final static short hero_proficiency_update = 1111; //英雄升级
	
	// 英雄模块
	public final static short msg_hero_list = 1201;		// 获取英雄列表
	public final static short msg_hero_add = 1202;		// 添加英雄
	
	// 房间模块
	public final static short msg_room_challenge_request = 1301;	// 挑战
	public final static short msg_room_challenge_notice = 1302;		// 挑战通知
	public final static short msg_room_challenge_response = 1303;	// 回应挑战
	public final static short msg_room_choose_member = 1304;		// 选择上阵队员
	public final static short msg_room_member_info = 1305;			// 广播推送上阵队员信息
	public final static short msg_room_operation_notice = 1306;		// 操作通知
	public final static short msg_room_choose_hero = 1307;			// 选择或禁用英雄
	public final static short msg_room_choose_hero_notice = 1308;	// 选择或禁用英雄通知
//	public final static short msg_room_choose_hero = 1309;			// 选择英雄
//	public final static short msg_room_choose_hero_notice = 1310;	// 选择英雄通知
	public final static short msg_room_show_layout = 1311;			// 阵容展示通知
	
	// 战斗模块
	public final static short msg_fight_load_finish = 1401;				// 请求开始游戏
	public final static short msg_fight_begin_game_notice = 1402;		// 开始游戏通知
	public final static short msg_fight_get_hero_node_list = 1403;		// 获取英雄节点列表
	public final static short msg_fight_get_build_node_list = 1404;		// 获取建筑物列表
	public final static short msg_fight_get_monster_node_list = 1405;	// 获取野怪列表
	public final static short msg_fight_get_soldier_node_list = 1406;	// 获取军队(包含小兵，炮兵，超级兵)列表
//	public final static short msg_fight_move_to_dest = 1407;			// 通知客户端所有英雄移动到目的地
	public final static short msg_fight_soldier_create_notice = 1408;	// 通知出兵
//	public final static short msg_fight_move_start = 1409;				// 通知客户端节点移动到目标位置
	public final static short msg_fight_hp_notice = 1410;				// 通知客户端节点血量
//	public final static short msg_fight_move_stop = 1411;				// 通知客户端停止移动
	public final static short msg_fight_sync_location = 1412;			// 同步位置
	public final static short msg_fight_sync_frame = 1413;				// 同步帧
	public final static short msg_fight_pve_reward = 1414;				//PVE发放奖励
	
	//道具模块
	public final static short msg_item_get_list = 1501;			//获取用户背包
	public final static short msg_item_use = 1502;				//使用道具
	public final static short msg_item_change = 1503;			//道具变化通知
	public final static short msg_item_add = 1504;				//增加道具，测试用方法，后期关闭
	public final static short msg_item_Gacha = 1505;				//抽奖
	public final static short msg_item_GachaInfo = 1506;				//获取用户抽奖信息

	//副本模块
//  public final static short msg_duplicate_initialize = 1601;					//初始化章节 
	public final static short msg_duplicate_chapter_info = 1602;				//查询所有章节进度信息
	public final static short msg_duplicate_checkpoint_info = 1603;				//获取章节内关卡进度
	public final static short msg_duplicate_procedures = 1604;					//通关关卡
	public final static short msg_duplicate_get_box = 1605;					//领取宝箱
	public final static short msg_duplicate_get_saoDang = 1606;				//关卡扫荡
	
	//邮件模块
	public final static short msg_mail_send_mail = 1701;				//系统发邮件
	public final static short msg_mail_get_email = 1702;				//查看我的邮箱
	public final static short msg_mail_del = 1703;						//删除邮件
	public final static short msg_mail_get_attachment = 1704;					//领取附件
	public final static short msg_mail_a_key = 1705;					//一键删除或者领取
	public final static short msg_mail_automatic_del = 1706;				//自动删除推送
	public final static short msg_mail_read_mail = 1707;				//阅读邮件
	
	//好友模塊
	public final static short msg_friend_get_list = 1801;				//获得好友列表
	public final static short msg_friend_set_physicals = 1802;				//批量赠送体力
	public final static short msg_friend_get_physicals = 1803;						//批量收取体力
	public final static short msg_friend_del = 1804;					//删除好友
	public final static short msg_friend_recommend = 1805;					//推荐好友
	public final static short msg_friend_search = 1806;				//搜索好友
	public final static short msg_friend_apply_add = 1807;				//申请加好友
	public final static short msg_friend_get_apply = 1808;					//获得我的申请列表
	public final static short msg_friend_dispose_apply = 1809;				//批处理好友申请
	public final static short msg_friend_get_blacklist = 1810;				//获得我的我的黑名单
	public final static short msg_friend_remove_toBlacklist = 1811;				//从黑名单移出好友
	public final static short msg_friend_add_toBlacklist = 1812;				//加入黑名单
	
	//聊天模块
	public final static short msg_chat_send_msg = 1901;				//发送消息到世界
	public final static short msg_chat_private = 1902;				//私聊
	public final static short msg_chat_get_private_friend = 1903;				//获得私聊好友
	public final static short msg_chat_get_private_context = 1904;				//获取历史私聊好友
	public final static short msg_chat_send_toWorld = 1905;				//根据好友id获得对方名片
	
	public final static short msg_status_success = 0;
	public final static short msg_status_warning = 1;
	public final static short msg_status_failed = 2;				//失败
	public final static short msg_status_error = -1;
	
}

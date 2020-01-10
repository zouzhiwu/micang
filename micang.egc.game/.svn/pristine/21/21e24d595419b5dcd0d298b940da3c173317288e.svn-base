package com.game.entity;

import java.util.ArrayList;
import java.util.List;

import com.common.entity.Account;
import com.common.entity.Hero;
import com.common.entity.Member;

public class Player {
	// 玩家账户
	public Account account;
	// 房间号
	public int roomId;
	// 选择的队员
	public Member[] members = new Member[5];	// 顺序：上中野辅ADC
	// 选择的英雄
	public Hero[] heros = new Hero[5];	// 顺序：上中野辅ADC
	// 选择的角色数组
//	public Role[] roles = new Role[5];	// 顺序：上中野辅ADC
	// 被对方禁用的英雄模板Id列表
	public List<Integer> banHeroTemplateIdList = new ArrayList<Integer>(3);
	// 玩家是否选择了队员，用来控制是否自动选择队员Job
	public boolean isChoosed = false;
	// 客户端是否加载完成游戏场景
	public boolean isLoadFinish = false;
}

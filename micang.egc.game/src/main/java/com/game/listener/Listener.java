package com.game.listener;

import java.lang.reflect.Method;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.action.MethodClazz;
import com.cb.lisener.AbsLisener;
import com.cb.msg.Action;
import com.cb.util.Constant;
import com.cb.util.PackageUtil;
import com.common.entity.Account;
import com.common.enumerate.AccountState;
import com.common.enumerate.ProfileType;
import com.common.util.AssertUtil;
import com.game.config.AppConfig;
import com.game.dao.AccountDao;
import com.game.entity.Player;
import com.game.entity.Room;
import com.game.factory.Context;
import com.game.model.Model;

import io.netty.channel.Channel;

@Service
public class Listener extends AbsLisener {
	private static final Logger logger = LoggerFactory.getLogger(Listener.class);
	@Autowired
	private AccountDao accountDao;
	
	public boolean testToken(Long accountId, Integer tokenDirty) {
		/*if (serverConfig.isTest()) {
			return true;
		}
		if (accountId == 0) {
			return false;
		}
		if (tokenDirty == null) {
			return false;
		}
		String token = accountService.filterDirty(tokenDirty);
		Channel channel = ChannelCache.getChannel(accountId);
		String strToken = ChannelCache.getToken(channel);
		boolean result = StringUtils.equals(strToken, token);
		if (!result) {
			logger.error(String.format("test token=%s strToken=%s fail", token, strToken));
		}
		return result;*/
		return true;
	}
	
	public void channelInactive(Channel currChannel) {
		Long accountId = currChannel.attr(Constant.accountId).get();
		logger.info(String.format("玩家(%d)断开连接", accountId));
		Account account = Model.getInstance().accountMap.get(accountId);
		// 设置账户为下线状态
		account.setState(AccountState.Offline);
		accountDao.update(account);
		if (Model.getInstance().playerMap.containsKey(accountId)) {
			Player player = Model.getInstance().playerMap.get(accountId);
			if (Model.getInstance().roomMap.containsKey(player.roomId)) {
				Room room = Model.getInstance().roomMap.get(player.roomId);
				if (room.bluePlayer != null && room.bluePlayer.account.getState() == AccountState.Offline 
						&& room.redPlayer != null && room.redPlayer.account.getState() == AccountState.Offline) {
					// 如果在生产环境下，则创建已经Job回收房间资源，否则立即回收房间资源
					if (AppConfig.getProfile() == ProfileType.Production) {
						room.startDestroy(60 * 1000);
					} else {
						room.destroy();
					}
				}
			}
		}
	}

    public void scan() {
		Set<Class<?>> clazzSet = PackageUtil.getClasses("com.game.action");
		for (Class<?> clazz : clazzSet) {
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				Action[] actions = method.getAnnotationsByType(Action.class);
				for (Action action : actions) {
					if (method.isAnnotationPresent(Action.class)) {
						Object instance = Context.getBean(clazz);
						AssertUtil.asErrorTrue(!actionMap.containsKey(action.value()), String.format("Action=%s重复", action.value()));
						actionMap.put(action.value(), new MethodClazz(method, instance));
					}
				}
			}
		}
    }

}

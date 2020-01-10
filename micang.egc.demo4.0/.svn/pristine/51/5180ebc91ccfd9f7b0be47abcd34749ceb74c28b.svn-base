package com.game.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.common.entity.Account;
import com.game.entity.Player;
import com.game.entity.Room;


public class Model {
	private static Model instance;
	
	public Map<Long, Account> accountMap = new ConcurrentHashMap<Long, Account>();	// 账户Map
	public Map<Integer, Room> roomMap = new ConcurrentHashMap<Integer, Room>();	// 房间Map
	public AtomicInteger roomId = new AtomicInteger(0);
	public Map<Long, Player> playerMap = new ConcurrentHashMap<Long, Player>();
	
	public static Model getInstance() {
		if (instance == null) {
			instance = new Model();
		}
		return instance;
	}
	
}

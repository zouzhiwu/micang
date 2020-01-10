package com.game.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.factory.SocketServer;
import com.cb.util.ContextUtil;
import com.game.listener.Listener;

@Service
public class Server {
	private static final Logger logger = LoggerFactory.getLogger(Server.class);
	
	@Autowired
	private Listener listener;
	
	public static Integer gamePort = 9002;
	
	private static Server server = null;
	
	static {
		server = (Server)ContextUtil.getBean(Server.class);
	}
	
	public void gameServer(int gameServerPort) {
		listener.scanCbAction();
		listener.scanPbAction();
		logger.info("Listener Scan finish.");
		logger.info("App Config finish.");
		SocketServer.start(gameServerPort, listener);
		logger.info(String.format("tcp socket service port=%d finish.", gameServerPort));
		logger.info("Game server finish.");
		// 清除因上次非法关机在数据库Member表留下的ip地址和port号
		logger.info("kick role finish.");
	}
	
	public static void main(String[] args) {
		String strGamePort = args[0];
		Integer gamePort = Integer.parseInt(strGamePort);
		Server.gamePort = gamePort;
		server.gameServer(gamePort);
	}
	
	public static void stop() {
		System.exit(0);
	}
	
}

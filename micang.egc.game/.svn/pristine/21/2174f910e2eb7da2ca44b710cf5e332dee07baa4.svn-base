package com.zookeeper.test;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Server {
	private static final Logger logger = LoggerFactory.getLogger(Server.class);
	private static final String HOST_ADDRESS = "192.168.0.170:2181";
	private static final int DEFAULT_TIMEOUT = 2000;
	private static final String DEFAULT_SERVER_PARENT = "/servers";

	private ZooKeeper zkConnect = null;

	/**
	 * 连接至ZooKeeper
	 * 
	 * @throws Exception
	 */
	public void connect() throws Exception {
		zkConnect = new ZooKeeper(HOST_ADDRESS, DEFAULT_TIMEOUT, new Watcher() {
			public void process(WatchedEvent watchedEvent) {
				logger.info("Type:" + watchedEvent.getType() + " Path:" + watchedEvent.getPath());
			}
		});
	}

	/**
	 * 向ZooKeeper注册本服务器节点
	 * 
	 * @param data
	 *            服务器信息
	 * @throws Exception
	 */
	public void register(String data) throws Exception {
		String create = zkConnect.create(DEFAULT_SERVER_PARENT + "/server", data.getBytes(),
				ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL); // 注册成ephemeral节点以便自动在zk上注销
		logger.info(create + " is registered!");
	}

	/**
	 * 通过sleep模拟服务器在线
	 */
	public void sleep() {
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	public static void main(String[] args) throws Exception {

		// 连接至zk
		Server server = new Server();
		server.connect();

		// 向zk注册服务器信息
		String data = "h2";
		server.register(data);

		server.sleep();
	}
}

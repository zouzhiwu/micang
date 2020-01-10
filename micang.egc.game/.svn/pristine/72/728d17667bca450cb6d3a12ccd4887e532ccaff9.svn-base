package com.zookeeper.test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Client {
	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	private static final String HOST_ADDRESS = "192.168.0.170:2181,192.168.0.170:2182,192.168.0.170:2183";
    private static final int DEFAULT_TIMEOUT = 2000;
    private static final String DEFAULT_SERVER_PARENT = "/servers";

    private ZooKeeper zkConnect = null;

    /**
     * 连接至ZooKeeper
     * @throws Exception
     */
    public void connect() throws Exception {
        zkConnect = new ZooKeeper(HOST_ADDRESS, DEFAULT_TIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    updateServerCondition();    // 重复注册
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        });
    }

    /**
     * 向zk查询服务器情况, 并update本地服务器列表
     * @throws Exception
     */
    public void updateServerCondition() throws Exception {
        List<String> children = zkConnect.getChildren(DEFAULT_SERVER_PARENT, true);
        List<String> servers = new ArrayList<String>();
        for(String child : children) {
            byte[] data = zkConnect.getData(DEFAULT_SERVER_PARENT + "/" + child,
                                        false,
                                        null);
            servers.add(new String(data));
        }
        logger.info(Arrays.toString(servers.toArray(new String[0])));
    }
    
	public void create(String path, byte[] data) {
		try {
			zkConnect.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		} catch (KeeperException e) {
			logger.error(e.toString());
		} catch (InterruptedException e) {
			logger.error(e.toString());
		}
	}

    /**
     * 通过sleep让客户端持续运行，模拟"监听"
     */
    public void sleep() throws Exception{
    	logger.info("client is working");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {

        // 连接zk
        Client client = new Client();
        client.connect();

        // 获取servers节点信息（并监听），从中获取服务器信息列表
        client.updateServerCondition();
        
        client.create("/servers/server1", "1".getBytes());

        client.sleep();
    }
}

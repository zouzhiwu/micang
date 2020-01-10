package com.zookeeper.watched;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZKServer {
	ZooKeeper zk = null;
    private String parentNode = "/servers";

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String childNode = "hd1-1";
        ZKServer zkServer = new ZKServer();
        //获取连接
        zkServer.getConnection();
        //注册信息
        zkServer.regist(childNode);
        //业务逻辑，提示上线
        zkServer.build(childNode);

    }

    private void build(String hostname) throws InterruptedException {
        System.out.println(hostname + "上线了！！");
        Thread.sleep(Long.MAX_VALUE);
    }

    private void regist(String hostname) throws KeeperException, InterruptedException {
        String path = zk.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(path);
    }

    private void getConnection() throws IOException {
        zk = new ZooKeeper("192.168.0.170:2181,192.168.0.170:2182,192.168.0.170:2183", 3000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}

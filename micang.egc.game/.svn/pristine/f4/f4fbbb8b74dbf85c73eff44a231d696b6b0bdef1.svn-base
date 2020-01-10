package com.zookeeper.watched;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZKClient {
	ZooKeeper zk = null;
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZKClient zkClient = new ZKClient();
        zkClient.getConnection();
        zkClient.watching();
    }

    private void watching() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void getConnection() throws IOException {
        zk = new ZooKeeper("192.168.0.170:2181,192.168.0.170:2182,192.168.0.170:2183", 3000, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                try {
                    List<String> children = zk.getChildren("/servers", true);
                    ArrayList<String> node = new ArrayList<String>();
                    for (String c:children){
                        byte[] data = zk.getData("/servers/" + c, true, null);
                        node.add(new String(data));
                    }
                    System.out.println(node);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

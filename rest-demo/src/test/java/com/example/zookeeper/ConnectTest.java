package com.example.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author yangkun
 * generate on 2017/3/14
 */
public class ConnectTest {

    private static final int SESSION_TIMEOUT = 30 * 1000;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zk = new ZooKeeper("172.16.84.144:2181,172.16.84.145:2181,172.16.84.146:2181",
                ConnectTest.SESSION_TIMEOUT, watchedEvent -> System.out.println("Triggered :[" + watchedEvent.getPath() + "] [" + watchedEvent.getType() + "]event."));
//        if(!zk.getState().equals(ZooKeeper.States.CONNECTED)) {
//            while(true) {
//                if(zk.getState().equals(ZooKeeper.States.CONNECTED)) {
//                    break;
//                }
//                TimeUnit.SECONDS.sleep(5);
//            }
//        }

        zk.create("/mynode1", "helloworld".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zk.create("/mynode1/child1", "child1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //修改子节点要在父节点加监控
        System.out.println("Child1 Data:[" + zk.getChildren("/mynode1", true));
        zk.setData("/mynode1/child1", "child1changed".getBytes(), -1);
        //mynode1 watcher
        System.out.println("Znode Status:[" + zk.exists("/mynode1", true) + "]");
        zk.create("/mynode1/child2", "child2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        //child2 watcher
        System.out.println("Child2 Data:[".concat(new String(zk.getData("/mynode1/child2", true, null))).concat("]"));
        zk.delete("/mynode1/child2", -1);
//        zk.exists("/mynode1/child1", true);
        //没有child1的watcher，此处删除操作不会触发监控
        zk.delete("/mynode1/child1", -1);
        zk.delete("/mynode1", -1);
        zk.close();
    }
}

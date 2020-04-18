package pers.masteryourself.tutorial.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * <p>description : ZKWatcherApplication, 使用自定义的监听器
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/18 22:42
 */
public class ZkWatcherApplication {

    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.89.210:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    System.out.println("zk 连接成功");
                    latch.countDown();
                }
            }
        });
        latch.await();
        Stat stat = new Stat();
        // 使用自定义的监听器
        zooKeeper.getData("/masteryourself", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (Watcher.Event.EventType.NodeDataChanged == watchedEvent.getType()) {
                    System.out.println("/masteryourself 节点发生了变化：" + stat.toString());
                }
            }
        }, stat);
        System.in.read();
    }

}

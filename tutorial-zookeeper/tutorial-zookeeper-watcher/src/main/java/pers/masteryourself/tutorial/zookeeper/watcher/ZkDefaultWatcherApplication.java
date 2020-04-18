package pers.masteryourself.tutorial.zookeeper.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * <p>description : ZkDefaultWatcherApplication, 使用 zk 的默认监听器
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/18 23:02
 */
public class ZkDefaultWatcherApplication {

    public static void main(String[] args) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.89.210:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("这个是默认的监听器：" + watchedEvent);
                latch.countDown();
            }
        });
        latch.await();
        Stat stat = new Stat();
        // 为 true 表示使用默认的监听器，即初始化 ZooKeeper() 时所用的监听器
        zooKeeper.getData("/masteryourself", true, stat);
        System.in.read();
    }

}

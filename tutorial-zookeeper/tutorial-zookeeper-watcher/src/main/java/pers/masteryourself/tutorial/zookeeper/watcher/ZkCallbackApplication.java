package pers.masteryourself.tutorial.zookeeper.watcher;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * <p>description : ZkCallbackApplication
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/18 23:07
 */
public class ZkCallbackApplication {

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
        // 使用 callback 回调函数
        zooKeeper.getData("/masteryourself", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
                System.out.println("获取到的数据是：" + new String(bytes));
            }
        }, null);
        System.in.read();
    }

}

package pers.masteryourself.tutorial.zookeeper.election;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : LeaderSelectorApplication
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/19 0:56
 */
public class LeaderSelectorApplication {

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString("192.168.89.210:2181")
                    // 连接超时时间，默认为 15000 ms
                    .connectionTimeoutMs(5000)
                    // 会话超时时间，默认为 60000 ms
                    .sessionTimeoutMs(5000)
                    // 重试策略
                    .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                    .build();
            client.start();
            LeaderSelector leaderSelector = new LeaderSelector(client, "/LeaderSelector", new LeaderSelectorListener() {
                @Override
                public void takeLeadership(CuratorFramework client) throws Exception {
                    System.out.println("当前 leader 是：" + client + " ===>>> " + new Date());
                    // 每个 leader 保持 5s 连接，这个方法一旦执行完成后，就会释放 leader 权利
                    TimeUnit.SECONDS.sleep(5);
                }

                @Override
                public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                }
            });
            leaderSelector.start();
        }
        System.in.read();
    }

}

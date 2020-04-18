package pers.masteryourself.tutorial.zookeeper.election;

import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>description : ElectionApplication, 领导者选举
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/19 0:46
 */
public class LeaderLatchApplication {

    public static void main(String[] args) throws Exception {
        List<LeaderLatch> leaderLatches = Lists.newArrayList();
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
            LeaderLatch leaderLatch = new LeaderLatch(client, "/LeaderLatch", "client_" + i);
            leaderLatches.add(leaderLatch);
            leaderLatch.start();
        }
        TimeUnit.SECONDS.sleep(3);
        for (LeaderLatch leaderLatch : leaderLatches) {
            if (leaderLatch.hasLeadership()) {
                System.out.println("当前 leader 信息是：" + leaderLatch.getId());
                break;
            }
        }
        for (LeaderLatch leaderLatch : leaderLatches) {
            leaderLatch.close();
        }
    }

}

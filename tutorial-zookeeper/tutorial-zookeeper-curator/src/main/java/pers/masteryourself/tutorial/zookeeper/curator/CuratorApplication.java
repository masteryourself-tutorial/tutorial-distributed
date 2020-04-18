package pers.masteryourself.tutorial.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * <p>description : CuratorApplication
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/18 23:51
 */
public class CuratorApplication {

    public static void main(String[] args) throws Exception {
        // 1. 创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.89.210:2181")
                // 连接超时时间，默认为 15000 ms
                .connectionTimeoutMs(5000)
                // 会话超时时间，默认为 60000 ms
                .sessionTimeoutMs(5000)
                // 重试策略
                .retryPolicy(new ExponentialBackoffRetry(1000, 10))
                .build();
        // 2. 开启连接
        client.start();
        // 3. 创建临时节点
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/temp", "创建临时节点".getBytes());
    }

}

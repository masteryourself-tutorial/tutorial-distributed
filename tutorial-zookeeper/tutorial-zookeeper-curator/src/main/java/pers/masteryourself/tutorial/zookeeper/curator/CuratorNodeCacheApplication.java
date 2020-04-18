package pers.masteryourself.tutorial.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * <p>description : CuratorNodeCacheApplication
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/4/19 0:07
 */
public class CuratorNodeCacheApplication {

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
        // 3. 添加监听器
        NodeCache cache = new NodeCache(client, "/masteryourself");
        // 必须要调用 start 方法，参数为 true 表示在初始化时会调用 internalRebuild() 方法赋值
        cache.start(false);
        cache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("路径为：" + cache.getCurrentData().getPath());
                System.out.println("数据为：" + new String(cache.getCurrentData().getData()));
                System.out.println("状态为：" + cache.getCurrentData().getStat());
                System.out.println("---------------------------------------");
            }
        });
        // 4. 修改 /masteryourself 节点内容
        client.setData().forPath("/masteryourself", "值发生变化了".getBytes());
        System.in.read();
    }

}

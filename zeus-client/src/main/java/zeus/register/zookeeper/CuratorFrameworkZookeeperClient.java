package zeus.register.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

public class CuratorFrameworkZookeeperClient extends ZooKeeperClient{

    private final CuratorFramework client;
    
    public CuratorFrameworkZookeeperClient(String host,Integer retries,Integer con,Integer session){
         
        client = CuratorFrameworkFactory.builder()
                .connectString(host)
                .retryPolicy(new RetryNTimes(retries, 1000))
                .connectionTimeoutMs(con)
                .sessionTimeoutMs(session)
                .build();
        client.start();
    }


    @Override
    public void create(String path) throws Exception {
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
    }
}

package zeus.register.zookeeper;


public class CuratorFrameworkZookeeperTransporter implements ZookeeperTransporter {

    @Override
    public ZooKeeperClient connect(String host, Integer sessionTimeOut, Integer connectionTimeOut, Integer retries) throws Exception {
        return new CuratorFrameworkZookeeperClient(host,retries,connectionTimeOut,sessionTimeOut);
    }
}

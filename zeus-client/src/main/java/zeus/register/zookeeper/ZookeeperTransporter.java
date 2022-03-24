package zeus.register.zookeeper;

public interface ZookeeperTransporter {

    ZooKeeperClient connect(String host, Integer sessionTimeOut, Integer connectionTimeOut, Integer retries) throws Exception;

}

package zeus.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZeusWorkerCuratorConfiguration {

    @Value("zookeeper.retries")
    private Integer retries;

    @Value("zookeeper.host")
    private String host;

    @Value("zookeeper.sessionTimeOut")
    private Integer sessionTimeOut;

    @Value("zookeeper.connectionTimeOut")
    private Integer connectionTimeOut;

    @Bean
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, retries);
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString(host)
                .sessionTimeoutMs(sessionTimeOut)
                .connectionTimeoutMs(connectionTimeOut)
                .retryPolicy(retryPolicy)
                .build();
        curator.start();
        return curator;
    }

}

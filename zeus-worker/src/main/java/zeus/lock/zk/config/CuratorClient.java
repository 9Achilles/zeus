package zeus.lock.zk.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CuratorClient {

    @Scope("singleton")
    @Bean("ZKCurator")
    public CuratorFramework getCuratorFramework(){

        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .build();

        curatorFramework.start();
        return curatorFramework;
    }





}

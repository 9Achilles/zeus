package zeus.register;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zeus.config.EnableZeusConfigSelector;
import zeus.constant.ZeusAnnotation;
import zeus.constant.ZeusDeptConstant;
import zeus.util.DateUtil;
import zeus.util.SystemUtil;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.StringJoiner;

@Configuration
public class ZeusRegisterConfigure{

    @Resource
    private ZeusAnnotation zeusAnnotation;

    @Resource
    private CuratorFramework curatorFramework;


    @Bean
    public CuratorFramework curatorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,zeusAnnotation.getRetries());
        CuratorFramework curator = CuratorFrameworkFactory.builder()
                .connectString(zeusAnnotation.getHost())
                .sessionTimeoutMs(zeusAnnotation.getSessionTimeOut())
                .connectionTimeoutMs(zeusAnnotation.getConnectionTimeOut())
                .retryPolicy(retryPolicy)
                .build();
        curator.start();
        System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CONNECT ZOOKEEPER SUCCESS!"));
        return curator;
    }

    @Bean
    public ZeusAnnotation zeusAnnotation() {
        ZeusAnnotation zeusAnnotation = new ZeusAnnotation();
        zeusAnnotation.setHost(EnableZeusConfigSelector.host);
        zeusAnnotation.setDept(EnableZeusConfigSelector.dept);
        zeusAnnotation.setName(EnableZeusConfigSelector.name);
        zeusAnnotation.setRetries(EnableZeusConfigSelector.retries);
        zeusAnnotation.setSessionTimeOut(EnableZeusConfigSelector.sessionTimeOut);
        zeusAnnotation.setConnectionTimeOut(EnableZeusConfigSelector.connectionTimeOut);
        return zeusAnnotation;
    }

    public void createNode(String dept,String name,String host) throws Exception {
        StringJoiner joiner = new StringJoiner("/");
        String path = joiner.add(dept).add(name).add(host).toString();
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    @PostConstruct
    private void register(){
        //dept info
        String dept = zeusAnnotation.getDept();
        if (dept.equals(ZeusDeptConstant.ZEUS_PUBLIC_DEPT)) {
            dept = ZeusDeptConstant.ZEUS_PUBLIC_DEPT;
        } else {
            dept = ZeusDeptConstant.ZEUS_PRIVATE_DEPT.concat("_").concat(dept);
        }

        //name info
        String name = zeusAnnotation.getName();
        if (StringUtils.isEmpty(name)) {
            System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CONNECT ZOOKEEPER FAILED! CAUSED BY NAME IS NULL"));
            return;
        }

        try {
            String port = SystemUtil.getLocalPort();
            String ip = SystemUtil.getLocalIP();
            String nodeName = ip.concat(":").concat(port);
            createNode(dept, name, nodeName);
            System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CREATE ZOOKEEPER NODE SUCCESS! IP IS ").concat(nodeName));
        }catch (Exception exception){
            System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CREATE ZOOKEEPER FAILED! CAUSED BY ").concat(exception.getMessage()));
            return;
        }
    }

}

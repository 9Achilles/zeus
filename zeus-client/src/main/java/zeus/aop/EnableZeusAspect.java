package zeus.aop;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import zeus.annotation.EnableZeus;
import zeus.constant.ZeusZKDeptConstant;
import zeus.util.DateUtil;
import zeus.util.SystemUtil;
import java.util.Optional;
import java.util.StringJoiner;

@Aspect
@Component
public class EnableZeusAspect {

    //connect is ok
    private boolean tag = false;

    private void createNode(CuratorFramework curatorFramework,String dept, String name, String host) throws Exception {
        StringJoiner joiner = new StringJoiner("/","/","");
        String path = joiner.add(dept).add(name).add(host).toString();
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    @Pointcut("@annotation(zeus.annotation.EnableZeus)")
    private void EnableZeus() {
    }


    @Around(value = "EnableZeus()")
    private void register(ProceedingJoinPoint point) {

        if(tag){
            return;
        }

        EnableZeus enableZeus = point.getTarget().getClass().getAnnotation(EnableZeus.class);

        //start zk connect
        if(StringUtils.isEmpty(enableZeus.host())){
            return;
        }
        CuratorFramework connect = connect(enableZeus.host(), enableZeus.sessionTimeOut(), enableZeus.connectionTimeOut(), enableZeus.retries());

        if(!Optional.ofNullable(connect).isPresent()){
            return;
        }

        //start create node
        String dept = enableZeus.dept();
        if (dept.equals(ZeusZKDeptConstant.ZEUS_PUBLIC_DEPT)) {
            dept = ZeusZKDeptConstant.ZEUS_PUBLIC_DEPT;
        } else {
            dept = ZeusZKDeptConstant.ZEUS_PRIVATE_DEPT.concat("_").concat(dept);
        }

        String name = enableZeus.name();
        if (StringUtils.isEmpty(name)) {
            System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CONNECT ZOOKEEPER FAILED! CAUSED BY NAME IS NULL"));
            return;
        }

        try {
            String port = SystemUtil.getLocalPort();
            String ip = SystemUtil.getLocalIP();
            String nodeName = ip.concat(":").concat(port);
            createNode(connect,dept, name, nodeName);
            System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CREATE ZOOKEEPER NODE SUCCESS! IP IS ").concat(nodeName));
        } catch (Exception exception) {
            System.out.println(DateUtil.getStringDate().concat("  INFO  ZEUS CREATE ZOOKEEPER FAILED! CAUSED BY ").concat(exception.getMessage()));
            return;
        }
    }

    private CuratorFramework connect(String host, Integer sessionTimeOut, Integer connectionTimeOut, Integer retries) {
        try {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, retries);
            CuratorFramework curator = CuratorFrameworkFactory.builder()
                    .connectString(host)
                    .sessionTimeoutMs(sessionTimeOut)
                    .connectionTimeoutMs(connectionTimeOut)
                    .retryPolicy(retryPolicy)
                    .build();
            curator.start();
            tag = true;
            return curator;
        }catch (Exception exception){
            return null;
        }
    }


}

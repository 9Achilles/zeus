package zeus.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import zeus.annotation.EnableZeus;
import zeus.register.zookeeper.ZooKeeperClient;
import zeus.register.zookeeper.ZookeeperTransporter;
import zeus.util.SystemUtil;

import javax.annotation.Resource;
import java.util.StringJoiner;

@Aspect
public class EnableZeusAspect {

    //connect is ok
    private boolean connected = false;

    @Resource
    private ZookeeperTransporter zookeeperTransporter;

    @Pointcut("@annotation(zeus.annotation.EnableZeus)")
    private void EnableZeus() {
    }


    @AfterReturning(value = "EnableZeus()")
    private void register(ProceedingJoinPoint point) {

        System.out.println("starting enable zeus!");

        //connected is success
        if(connected){
            return;
        }
        EnableZeus enableZeus = point.getTarget().getClass().getAnnotation(EnableZeus.class);

        try {
            //start connect
            ZooKeeperClient zooKeeperClient = zookeeperTransporter.connect(enableZeus.host(), enableZeus.sessionTimeOut(), enableZeus.connectionTimeOut(), enableZeus.retries());
            //create node
            StringJoiner joiner = new StringJoiner("/","/","");
            String port = SystemUtil.getLocalPort();
            String ip = SystemUtil.getLocalIP();
            String node = ip.concat(":").concat(port);
            String path = joiner.add(enableZeus.dept()).add(enableZeus.name()).add(node).toString();
            zooKeeperClient.create(path);
            connected = true;
            System.out.println("start enable zeus success");
        }catch (Exception exception){
            System.out.println("start enable zeus error");
        }
    }
}

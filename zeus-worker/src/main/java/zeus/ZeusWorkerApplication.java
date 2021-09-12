package zeus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import zeus.exception.ZeusWorkerException;
import zeus.service.ZeusWorkerCuratorService;
import zeus.util.ZeusWorkerSystemUtil;

import javax.annotation.PostConstruct;

/**
 * worker 负责发送rpc请求(kafka消费消息，自己找远程执行的机器（随机），自己)
 *        负责执行分片任务
 *        负责执行编排任务
 *        请求执行结果返回server
 *        保证mideng
 */
@EnableCaching
@SpringBootApplication
public class ZeusWorkerApplication {

    @Autowired
    private ZeusWorkerCuratorService zeusWorkerCuratorService;

    public static void main(String[] args) {
        SpringApplication.run(ZeusWorkerApplication.class);
    }

    @PostConstruct
    private void register() throws ZeusWorkerException {
        String path = "/zeus/workers/".concat(ZeusWorkerSystemUtil.getLocalIP()).concat(":").concat(ZeusWorkerSystemUtil.getLocalPort());
        zeusWorkerCuratorService.createNode(path);
    }



}

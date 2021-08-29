package zeus.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zeus.rpc.RPCService;
import zeus.util.HttpUtil;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduleService {

    @Resource
    private RPCService rpcService;

    @Value("zeus.ip")
    private String zeusIp;

    @Value("")
    private String applicationCode;

    @Value("")
    private String applicationName;

    //定时任务队列
    private final static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(50);


    {
        try {
            Map<String, Object> body = HttpUtil.buildHost(applicationCode, applicationName);
            scheduledThreadPool.scheduleAtFixedRate(() -> {
                rpcService.doPost(zeusIp.concat("/zeus/executor/check"), null, body, "utf-8");
            }, 1, 90, TimeUnit.SECONDS);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


}

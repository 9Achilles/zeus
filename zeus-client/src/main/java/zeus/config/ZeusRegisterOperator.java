package zeus.config;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lym
 */
@Configuration
public class ZeusRegisterOperator {

    @Resource
    private ZeusRegisterProperties zeusRegisterProperties;

    @Resource
    private CloseableHttpAsyncClient httpclient;

    private String url = "http://%s:%s/zeusApi/check";


    private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

    private static class ZeusRegisterProperties {

        @Value("${zeus.register.ip}")
        private String ip;

        @Value("${zeus.register.port}")
        private String port;

        @Value("${zeus.register.provider.key}")
        private String providerKey;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getProviderKey() {
            return providerKey;
        }

        public void setProviderKey(String providerKey) {
            this.providerKey = providerKey;
        }
    }

    @Bean
    public ZeusRegisterProperties zeusRegisterProperties(){
        return new ZeusRegisterProperties();
    }

    @PostConstruct
    public void init() {

        String checkUrl = getUrl();

        scheduled.scheduleAtFixedRate(() -> {
            try {
                HttpPost httpPost = new HttpPost(checkUrl);
                httpclient.execute(httpPost, null);
                System.out.println("执行成功！");
            } catch (Exception exception) {
                exception.getMessage();
                System.out.println("执行失败！");
            }

        }, 60, 60, TimeUnit.SECONDS);

    }

    private String getUrl() {
        return String.format(url, zeusRegisterProperties.getIp(), zeusRegisterProperties.getPort());
    }


}

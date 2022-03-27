package zeus.config.config;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zeus.constant.ZeusCommonConstant;
import zeus.util.AddressUtil;

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
    private ZeusProviderProperties zeusProviderProperties;

    private final ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);

    private static class ZeusRegisterProperties {

        @Value("${zeus.register.address}")
        private String address;

        @Value("${zeus.register.period}")
        private String period;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public String[] getAddressList() {
            return StringUtils.contains(this.address, ",") ? StringUtils.split(this.address, ",") : new String[]{this.address};
        }
    }

    private static class ZeusProviderProperties {

        @Value("${zeus.provider.ip}")
        private String ip;

        @Value("${zeus.provider.port}")
        private String port;

        @Value("${zeus.provider.key}")
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
    public ZeusRegisterProperties zeusRegisterProperties() {
        return new ZeusRegisterProperties();
    }

    @Bean
    public ZeusProviderProperties zeusProviderProperties() {
        return new ZeusProviderProperties();
    }

    @PostConstruct
    public void register() {

        scheduled.scheduleAtFixedRate(() -> {
            try {
                String[] addressList = zeusRegisterProperties.getAddressList();
                String[] registerUrl = parseRegisterUrl(addressList);
                for (String address : addressList) {
                    //发送

                }

            } catch (Exception exception) {
                exception.getMessage();
            }

        }, 60, Long.parseLong(zeusRegisterProperties.getPeriod()), TimeUnit.SECONDS);

    }

    private String[] parseRegisterUrl(String[] addressList) {

        String[] registerUrlList = new String[addressList.length];

        for (int i = 0; i < addressList.length; i++) {
            String[] item = StringUtils.split(addressList[i],":");
            registerUrlList[i] = AddressUtil.getUrl(ZeusCommonConstant.ZEUS_PROVIDER_REGISTER_URL, item[0], item[1]);
        }
        return registerUrlList;
    }


}

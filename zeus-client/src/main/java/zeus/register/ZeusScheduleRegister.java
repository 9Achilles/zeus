package zeus.register;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import zeus.dto.HostDTO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import zeus.annotation.ZeusSchedule;
import zeus.rpc.RPCService;
import zeus.util.HttpUtil;
import zeus.util.SystemUtil;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.print.DocFlavor;
import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.net.HttpCookie;
import java.net.InetAddress;
import java.util.*;

/**
 * 启动时注册
 */
@Component
public class ZeusScheduleRegister implements BeanPostProcessor {

    @Qualifier("HttpClientRPCService")
    @Autowired
    private RPCService rpcService;

    //  InputStream is = this.getClass().getResourceAsStream("/config.json");

    @Value("${zeus.ip}")
    private String zeusIp;

    @Value("")
    private String applicationCode;

    @Value("")
    private String applicationName;

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 注册
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            register(bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    //注册
    private void register(Object bean) throws Exception {

        Class<?> clazz = bean.getClass();

        Annotation[] annotations = clazz.getAnnotations();

        boolean check = check(annotations, new Class[]{Controller.class, RestController.class});

        if (!check) {
            return;
        }

        Annotation[] methodAnnotations = (Annotation[]) Arrays.stream(clazz.getMethods()).map(a -> a.getAnnotations()).toArray();
        check = check(methodAnnotations, new Class[]{ZeusSchedule.class});

        if (!check) {
            return;
        }

//        ZeusSchedule zeusSchedule = clazz.getAnnotation(ZeusSchedule.class);
//        String applicationCode = zeusSchedule.applicationCode();
//        if (StringUtils.isEmpty(applicationCode)) {
//            return;
//        }
//
//        String applicationName = zeusSchedule.applicationName();

        //注册
        Map<String, Object> body = HttpUtil.buildHost(applicationCode, applicationName);
        String url = zeusIp.concat("/zeus/executor/register");
        rpcService.doPost(url, null, body, "utf-8");
    }



    //校验
    private boolean check(Annotation[] checks, Class[] selects) {
        List<Class> annotations = Arrays.asList(selects);
        return Arrays.stream(checks).anyMatch(a -> annotations.contains(a.annotationType()));
    }


}

package zeus.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import zeus.dto.HostDTO;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import zeus.annotation.ZeusSchedule;
import zeus.rpc.RPCService;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.util.*;

/**
 * 启动时注册
 */
public class ZeusScheduleRegister implements BeanPostProcessor {

    @Qualifier("HttpClientRPCService")
    @Autowired
    private RPCService rpcService;

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

        ZeusSchedule.class.

        //注册
        Map<String,Object> hostDTO = buildHost();
        rpcService.doPost("",null,hostDTO,"utf-8");

    }

    private Map<String,Object> buildHost() throws Exception {
        Map<String,Object> map = new HashMap<>();
        map.put("host",getLocalIP());
        map.put("port",getLocalPort());

        return map;
    }


    //校验
    private boolean check(Annotation[] checks, Class[] selects) {
        List<Class> annotations = Arrays.asList(selects);
        return Arrays.stream(checks).anyMatch(a -> annotations.contains(a.annotationType()));
    }

    public static String getLocalPort() throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,*"), null);
        if (objectNames == null || objectNames.size() <= 0) {
            throw new IllegalStateException("Cannot get the names of MBeans controlled by the MBean server.");
        }
        for (ObjectName objectName : objectNames) {
            String protocol = String.valueOf(mBeanServer.getAttribute(objectName, "protocol"));
            String port = String.valueOf(mBeanServer.getAttribute(objectName, "port"));
            // windows下属性名称为HTTP/1.1, linux下为org.apache.coyote.http11.Http11NioProtocol
            if (protocol.equals("HTTP/1.1") || protocol.equals("org.apache.coyote.http11.Http11NioProtocol")) {
                return port;
            }
        }
        throw new IllegalStateException("Failed to get the HTTP port of the current server");
    }


    public static String getLocalIP() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }


}

package zeus.util;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Set;

public class SystemUtil {


    public static String getLocalPort() throws Exception {
//        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//        Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName("*:type=Connector,*"), null);
//        if (objectNames == null || objectNames.size() <= 0) {
//            throw new IllegalStateException("Cannot get the names of MBeans controlled by the MBean server.");
//        }
//        for (ObjectName objectName : objectNames) {
//            String protocol = String.valueOf(mBeanServer.getAttribute(objectName, "protocol"));
//            String port = String.valueOf(mBeanServer.getAttribute(objectName, "port"));
//            // windows下属性名称为HTTP/1.1, linux下为org.apache.coyote.http11.Http11NioProtocol
//            if (protocol.equals("HTTP/1.1") || protocol.equals("org.apache.coyote.http11.Http11NioProtocol")) {
//                return port;
//            }
//        }
        try(ServerSocket socket = new ServerSocket(0)) {
            int port = socket.getLocalPort();
            return String.valueOf(port);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

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

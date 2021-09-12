package zeus.util;

import java.net.InetAddress;
import java.net.ServerSocket;

public class SystemUtil {


    public static String getLocalPort() throws Exception {
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

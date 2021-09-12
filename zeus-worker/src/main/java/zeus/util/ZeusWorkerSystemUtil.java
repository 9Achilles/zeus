package zeus.util;

import zeus.exception.ZeusWorkerException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class ZeusWorkerSystemUtil {


    public static String getLocalPort() throws ZeusWorkerException {
        try(ServerSocket socket = new ServerSocket(0)) {
            int port = socket.getLocalPort();
            return String.valueOf(port);
        }catch (IOException e){
            throw new ZeusWorkerException(e.getMessage());
        }

    }


    public static String getLocalIP() throws ZeusWorkerException{
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new ZeusWorkerException(e.getMessage());
        }
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

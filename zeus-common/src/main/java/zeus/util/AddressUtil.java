package zeus.util;

/**
 * @author lym
 */
public class AddressUtil {

    public static String getUrl(String template,String ip,String port) {
        return String.format(template, ip, port);
    }

}

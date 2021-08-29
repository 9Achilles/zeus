package zeus.util;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    public static Map<String, Object> buildHost(String applicationCode, String applicationName) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("host", SystemUtil.getLocalIP());
        map.put("port", SystemUtil.getLocalPort());
        map.put("applicationCode", applicationCode);
        map.put("applicationName", applicationName);
        return map;
    }

}

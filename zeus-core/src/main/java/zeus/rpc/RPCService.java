package zeus.rpc;

import java.util.Map;

public interface RPCService {

    void doPost(String url, Map<String, String> headers, Map<String, Object> body, String charset);


}

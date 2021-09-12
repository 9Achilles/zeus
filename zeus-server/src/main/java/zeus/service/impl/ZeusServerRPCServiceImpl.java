package zeus.service.impl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zeus.callback.ZeusServerRPCCallBack;
import zeus.service.ZeusServerRPCService;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ZeusServerRPCServiceImpl implements ZeusServerRPCService {

    @Autowired
    private CloseableHttpAsyncClient httpclient;

    @Override
    public void doPost(String url, String head, String body) {
        HttpPost httpPost = new HttpPost(url);
        Map headers = JSON.parseObject(head, LinkedHashMap.class);
        setHeaders(httpPost, headers);
        httpPost = setBody(httpPost, body);
        httpclient.execute(httpPost, new ZeusServerRPCCallBack());
    }


    private HttpPost setBody(HttpPost httpPost, String body) {
        if (StringUtils.isNotEmpty(body)) {
            StringEntity entity = new StringEntity(body, "utf-8");
            httpPost.setEntity(entity);
        }
        return httpPost;
    }

    private void setHeaders(HttpPost httpPost, Map<String, String> headers) {
        if (Optional.ofNullable(headers).isPresent() && !headers.isEmpty()) {
            headers.keySet()
                    .stream()
                    .forEach(a -> httpPost.addHeader(a, headers.get(a)));
        }
    }



}

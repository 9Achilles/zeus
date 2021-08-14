package zeus.rpc.impl;

import com.alibaba.fastjson.JSON;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.stereotype.Service;
import zeus.callback.ZeusCallBack;
import zeus.rpc.RPCService;


import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Optional;

@Service("HttpClientRPCService")
public class HttpClientRPCServiceImpl implements RPCService {

    public static CloseableHttpAsyncClient httpclient = null;

    @PostConstruct
    public static void buildRPCPool() throws IOReactorException {

        IOReactorConfig ioReactorConfig = IOReactorConfig
                .custom()
                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
                .setSoKeepAlive(true)
                .build();

        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
        cm.setMaxTotal(100);

        httpclient = HttpAsyncClients.custom().setConnectionManager(cm).build();
        httpclient.start();
    }


    @Override
    public void doPost(String url, Map<String, String> headers, Map<String, Object> body, String charset) {
        HttpPost httpPost = new HttpPost(url);
        httpPost = addParam(httpPost, body, charset);
        httpclient.execute(httpPost, new ZeusCallBack());
    }


    private HttpPost addParam(HttpPost httpPost, Map<String, Object> body, String charset) {
        if (Optional.ofNullable(body).isPresent() && !body.isEmpty()) {
            String jsonBody = JSON.toJSONString(body);
            StringEntity entity = new StringEntity(jsonBody, charset);
            httpPost.setEntity(entity);
        }
        return httpPost;
    }




}




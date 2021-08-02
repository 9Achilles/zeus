package zeus.threadpool;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RPCPoolBean {

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

}

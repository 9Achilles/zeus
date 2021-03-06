//package zeus.config;
//
//import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
//import org.apache.http.impl.nio.client.HttpAsyncClients;
//import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
//import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
//import org.apache.http.impl.nio.reactor.IOReactorConfig;
//import org.apache.http.nio.reactor.ConnectingIOReactor;
//import org.apache.http.nio.reactor.IOReactorException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ZeusServerHttpAsyncClientConfiguration {
//
//    @Bean
//    public CloseableHttpAsyncClient closeableHttpAsyncClient() throws IOReactorException {
//
//        IOReactorConfig ioReactorConfig = IOReactorConfig
//                .custom()
//                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
//                .setSoKeepAlive(true)
//                .build();
//
//        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
//        PoolingNHttpClientConnectionManager cm = new PoolingNHttpClientConnectionManager(ioReactor);
//        cm.setMaxTotal(100);
//
//        CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom().setConnectionManager(cm).build();
//        httpclient.start();
//        return httpclient;
//    }
//
//}

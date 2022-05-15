package zeus.config.config;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author lym
 */
@Configuration
public class ZeusClient {

    private Bootstrap b = new Bootstrap();

    private EventLoopGroup group;

    @PostConstruct
    public void init() {
        group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("codec", new HttpServerCodec())                  // HTTP 编解码
                                .addLast("compressor", new HttpContentCompressor())       // HttpContent 压缩
                                .addLast("aggregator", new HttpObjectAggregator(65536)); // HTTP 消息聚合
                    }
                });
    }

    public void connect(String host, int port) {
        b.connect(host, port).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {

                if (future.isSuccess()) {

                } else {

                }
            }
        });
    }

    public void stop() {
        if (group != null) {
            group.shutdownGracefully();
            group = null;
        }
    }

    @Bean(name = "ZeusClient")
    public ZeusClient ZeusClient(){
        return new ZeusClient();
    }

}

package zeus.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadPoolConfiguration {

    public static final ThreadPoolExecutor dispatchThreadPool = new ThreadPoolExecutor(100, 500, 60000, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public static final ThreadPoolExecutor checkThreadPool = new ThreadPoolExecutor(10, 50, 30000, TimeUnit.SECONDS, new LinkedBlockingQueue());

    @PostConstruct
    public void init() {
        dispatchThreadPool.execute(() -> {
            System.out.println();
        });
        checkThreadPool.execute(() -> {
            System.out.println();
        });
    }

}

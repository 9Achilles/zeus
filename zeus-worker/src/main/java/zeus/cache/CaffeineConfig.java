package zeus.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    //300s
    @Bean(name = "executorHostCache")
    public CacheManager cacheManager(){

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine caffeine = Caffeine.newBuilder()
                .expireAfterAccess(300, TimeUnit.SECONDS)
                .expireAfter(new Expiry<String, String>() {
                    @Override
                    public long expireAfterCreate(
                            String key, String value, long currentTime) {
                        return 10l;
                    }
                    @Override
                    public long expireAfterUpdate(
                            String key, String value, long currentTime, long currentDuration) {
                        return 10l;
                    }
                    @Override
                    public long expireAfterRead(
                            String key, String value, long currentTime, long currentDuration) {
                        return 10l;
                    }
                })
                .initialCapacity(100)
                .maximumSize(1000);
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }





}

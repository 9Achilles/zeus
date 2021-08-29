package zeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ZeusScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusScheduleApplication.class);
    }

}

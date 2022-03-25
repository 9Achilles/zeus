package zeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import zeus.config.annotation.EnableZeus;

/**
 * 1 找一个worker，定时调度，corn时间表达式
 * 2 对任务进行管理，任务配置（部门，名称）
 * 3 一致性hash算法解决调度冗余的问题
 *
 *
 */
//@EnableAsync
@SpringBootApplication
@EnableZeus
public class ZeusServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZeusServerApplication.class);
    }

}

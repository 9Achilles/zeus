package zeus.service.impl;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.springframework.stereotype.Service;
import zeus.service.WhileTimerService;

import java.util.concurrent.TimeUnit;

@Service
public class WhileTimerServiceImpl implements WhileTimerService {

    private HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS,60);

    @Override
    public void push(TimerTask task, long delay, TimeUnit unit){
       hashedWheelTimer.newTimeout(task, delay, unit);
    }




}

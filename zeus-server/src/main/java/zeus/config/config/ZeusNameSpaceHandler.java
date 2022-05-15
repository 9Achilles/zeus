package zeus.config.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author lym
 */
@Component
public class ZeusNameSpaceHandler {

    @Resource
    private ZeusRegisterOperator zeusRegisterOperator;

    @Resource
    private ZeusServer zeusServer;

    @Resource
    private ZeusClient zeusClient;

    @PostConstruct
    public void init(){
        //创建server

        //创建client

        //注册+心跳
        zeusRegisterOperator.register();
    }



}

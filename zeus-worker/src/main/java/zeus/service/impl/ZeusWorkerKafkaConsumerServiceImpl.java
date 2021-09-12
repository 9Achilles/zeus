package zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import zeus.service.ZeusWorkerKafkaConsumerService;
import zeus.service.ZeusWorkerRPCService;

/**
 * 消费 工作节点的success or fail message
 */
@Service
public class ZeusWorkerKafkaConsumerServiceImpl implements ZeusWorkerKafkaConsumerService {

    @Autowired
    private ZeusWorkerRPCService zeusWorkerRPCService;


    @KafkaListener(topics = {"ZEUS_SERVER_SUBSCRIBE_SUCCESS_TOPIC"})
    public void customSuccess(){


    }

    @KafkaListener(topics = {"ZEUS_SERVER_SUBSCRIBE_FAIL_TOPIC"})
    public void customFail(){

    }



}

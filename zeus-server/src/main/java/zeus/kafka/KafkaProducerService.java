package zeus.kafka;

import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;


public class KafkaProducerService {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private String


    private String

    public void sendMessage(String message) {
        kafkaTemplate.send("", message);
    }



}

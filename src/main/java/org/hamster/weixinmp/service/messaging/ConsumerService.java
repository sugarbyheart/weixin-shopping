package org.hamster.weixinmp.service.messaging;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class ConsumerService {


    @RabbitListener(queues = "orderServiceQueue")
    public void receive(String message) {
        log.info("Received message '{}'", message);
    }

}

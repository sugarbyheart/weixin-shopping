package org.hamster.weixinmp.service.messaging;

import com.github.sugarbyheart.daigou.common.DTO.ItemDiscription;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;


@Slf4j
@Component
public class ProducerService {

    private final RabbitTemplate rabbitTemplate;
    private final TopicExchange topicExchange;

    @Value("${item.discription.routingKey}")
    private String routingKey;

    @Autowired
    public ProducerService(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    public void sendDiscription(ItemDiscription discription) {
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, discription);
        log.info("Send message: {}", discription.toString());
    }

}

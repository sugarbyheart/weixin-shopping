package org.hamster.weixinmp.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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

    private int messageNumber = 0;

    private static List<String> ROUTING_KEYS = Arrays.asList(
            "customer.created",
            "customer.edited",
            "customer.deleted",
            "order.created",
            "order.edited",
            "order.deleted",
            "invoice.created",
            "invoice.edited",
            "invoice.deleted");

    private Random random = new Random();

    @Autowired
    public ProducerService(RabbitTemplate rabbitTemplate, TopicExchange topicExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.topicExchange = topicExchange;
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendMessage() {
        String routingKey = randomRoutingKey();
        String message = String.format("Event no. %d of type '%s'", ++messageNumber, routingKey);
        rabbitTemplate.convertAndSend(topicExchange.getName(), routingKey, message);
        log.info("Published message '{}'", message);
    }

    private String randomRoutingKey() {
        return ROUTING_KEYS.get(random.nextInt(ROUTING_KEYS.size()));
    }
}

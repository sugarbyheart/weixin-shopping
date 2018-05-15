package org.hamster.weixinmp.service.messaging;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class ProducerConfiguration {

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public ProducerService customerService(RabbitTemplate rabbitTemplate, TopicExchange eventExchange) {
        return new ProducerService(rabbitTemplate, eventExchange);
    }
}

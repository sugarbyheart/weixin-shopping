package org.hamster.weixinmp.service.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class ProducerConfiguration {

    @Value("${exchange.name}")
    private String exchange;

    @Value("${item.discription.queue}")
    private String itemDiscriptionQueue;

    @Value("${item.discription.routingKey}")
    private String itemDiscriptionRoutingKey;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue queue() {
        return new Queue(itemDiscriptionQueue);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(itemDiscriptionRoutingKey);
    }
}

package com.ruoyi.common.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {
    public static final String TRANSACTION_QUEUE = "transaction";

    @Bean
    DirectExchange directExchange() {
        return DirectExchange.DEFAULT;
    }

    @Bean
    Queue transactionQueue() {
        return QueueBuilder.durable(TRANSACTION_QUEUE).build();
    }

    @Bean
    Binding transactionBinding(Queue transactionQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(transactionQueue).to(directExchange).with(TRANSACTION_QUEUE);
    }

    @Bean
    Jackson2JsonMessageConverter listenerMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(listenerMessageConverter());
        return template;
    }
}

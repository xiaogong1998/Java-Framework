package com.gong.rabbitmq.config;

import com.gong.rabbitmq.properties.RabbitMQProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * TODO
 *
 * @author xiaogong
 * @since 2023/10/19 16:49
 */
@Slf4j
@Configuration
@ConditionalOnClass({RabbitMQProperties.class})
public class RabbitMQConfiguration {

    @Bean
    public CachingConnectionFactory connectionFactory(RabbitMQProperties properties) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(properties.getHost());
        connectionFactory.setPort(properties.getPort());
        connectionFactory.setUsername(properties.getUsername());
        connectionFactory.setPassword(properties.getPassword());
        return connectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(CachingConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        log.info("rabbitMQ rabbitAdmin 初始化成功");
        return rabbitAdmin;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactor) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactor);
        log.info("rabbitMQ rabbitTemplate 初始化成功");
        return rabbitTemplate;
    }

    // @Bean
    // public CustomExchange delayedExchange() {
    //     Map<String, Object> args = new HashMap<>();
    //     // 自定义交换机的类型，指定分发方式
    //     args.put("x-delayed-type", "direct");
    //     // 此处type指定为延迟交换机
    //     CustomExchange delayedExchange = new CustomExchange("delayed_exchange", "x-delayed-message", true, false, args);
    //     log.info("延迟交换机：{}创建成功", delayedExchange.getName());
    //     return delayedExchange;
    // }

    // @Bean
    // public Queue myQueue() {
    //     Queue myQueue = QueueBuilder.durable("my_queue")
    //             .build();
    //     log.info("队列：{}创建成功", myQueue.getName());
    //     return myQueue;
    // }
    //
    // @Bean
    // public Binding queueBinding() {
    //     Binding myRoutingKey = BindingBuilder.bind(myQueue())
    //             .to(delayedExchange())
    //             .with("my_routing_key")
    //             .noargs();
    //     log.info("队列{}绑定路由{}成功", myQueue().getName(), "my_routing_key");
    //     return myRoutingKey;
    // }
}

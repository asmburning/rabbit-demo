package org.lxy.demo.rabbit.grf.mq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@Configuration
public class MqConfig {

    @Value(value = "${spring.rabbitmq.host}")
    private String host;
    @Value(value = "${spring.rabbitmq.port}")
    private int port;
    @Value(value = "${spring.rabbitmq.username}")
    private String username;
    @Value(value = "${spring.rabbitmq.password}")
    private String pwd;
    @Value(value = "${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(pwd);
        factory.setVirtualHost(virtualHost);
        return factory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());

        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置应答模式
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(8);
        //每次请求发送给每个消费者的消息数量
        factory.setPrefetchCount(250);
        //是否重回队列,true:当消息消费出现异常时，没有被catch的话，会被重新丢回队列头部，重新消费 false:直接丢弃
        factory.setDefaultRequeueRejected(false);
        return factory;
    }

}

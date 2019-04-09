package org.lxy.demo.rabbit.grf.mq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@Configuration
public class MqConfig {


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //设置应答模式
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(5);
        //每次请求发送给每个消费者的消息数量
        factory.setPrefetchCount(250);
        //是否重回队列,true:当消息消费出现异常时，没有被catch的话，会被重新丢回队列头部，重新消费 false:直接丢弃
        factory.setDefaultRequeueRejected(false);
        return factory;
    }

}

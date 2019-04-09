package org.lxy.demo.rabbit.skt.config;

import org.lxy.demo.rabbit.common.MqConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@Configuration
public class MqConfig {

    private String host = "127.0.0.1";
    private int port = 5672;
    private String username = "testmq";
    private String pwd = "testmq";
    private String virtualHost = "/test_vh";

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
    public List<Declarable> orderFanout(RabbitAdmin rabbitAdmin) {
        FanoutExchange orderFanoutExchange = new FanoutExchange(MqConstants.OrderFanout.EXCHANGER_NAME);
        List<Declarable> declarableList = new ArrayList<>();
        declarableList.add(orderFanoutExchange);
        for (String queueName : MqConstants.OrderFanout.QUEUE_SET) {
            Queue queue = new Queue(queueName, true, false, false);
            queue.setAdminsThatShouldDeclare(rabbitAdmin);

            declarableList.add(queue);
            declarableList.add(BindingBuilder.bind(queue).to(orderFanoutExchange));
        }
        return declarableList;
    }


    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());

        factory.setMessageConverter(new Jackson2JsonMessageConverter());
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

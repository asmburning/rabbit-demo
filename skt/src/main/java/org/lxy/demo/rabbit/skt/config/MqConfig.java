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
    private String username = "guest";
    private String pwd = "guest";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(pwd);
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
    public List<Declarable> orderDirect(RabbitAdmin rabbitAdmin) {
        DirectExchange orderDirectExchange = new DirectExchange(MqConstants.OrderDirect.EXCHANGER_NAME);
        List<Declarable> declarableList = new ArrayList<>();
        declarableList.add(orderDirectExchange);
        for (MqConstants.OrderDirect.OrderDirectQueue directQueue : MqConstants.OrderDirect.OrderDirectQueue.values()) {
            Queue queue = new Queue(directQueue.getQueueName(), true, false, false);
            queue.setAdminsThatShouldDeclare(rabbitAdmin);
            declarableList.add(queue);
            declarableList.add(BindingBuilder.bind(queue).to(orderDirectExchange).with(directQueue.getRoutingKey()));
        }
        return declarableList;
    }


    @Bean
    public List<Declarable> payTopic(RabbitAdmin rabbitAdmin) {
        TopicExchange payTopicExchange = new TopicExchange(MqConstants.PayTopic.EXCHANGER_NAME);
        List<Declarable> declarableList = new ArrayList<>();
        declarableList.add(payTopicExchange);

        Queue payQueue = new Queue(MqConstants.PayTopic.QT_PAY, true, false, false);
        payQueue.setAdminsThatShouldDeclare(rabbitAdmin);
        declarableList.add(payQueue);
        declarableList.add(BindingBuilder.bind(payQueue).to(payTopicExchange).with(MqConstants.PayTopic.QTRK_PAY));

        Queue collectPayQueue = new Queue(MqConstants.PayTopic.QT_PAY_COLLECT, true, false, false);
        collectPayQueue.setAdminsThatShouldDeclare(rabbitAdmin);
        declarableList.add(collectPayQueue);
        declarableList.add(BindingBuilder.bind(collectPayQueue).to(payTopicExchange).with(MqConstants.PayTopic.QTRK_PAY_COLLECT));

        return declarableList;
    }

    @Bean
    public List<Declarable> headers(RabbitAdmin rabbitAdmin) {
        HeadersExchange headersExchange = new HeadersExchange(MqConstants.OrderHeaders.EXCHANGER_NAME);
        List<Declarable> declarableList = new ArrayList<>();
        declarableList.add(headersExchange);

        Queue headersQueue = new Queue(MqConstants.OrderHeaders.QH_ORDER, true, false, false);
        headersQueue.setAdminsThatShouldDeclare(rabbitAdmin);
        declarableList.add(headersQueue);
        declarableList.add(BindingBuilder.bind(headersQueue).to(headersExchange).whereAny(MqConstants.OrderHeaders.QH_ORDER_KEY_TAG).exist());

        return declarableList;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());

        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //设置应答模式
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setConcurrentConsumers(5);
        factory.setMaxConcurrentConsumers(10);
        //每次请求发送给每个消费者的消息数量
        factory.setPrefetchCount(250);
        //是否重回队列,true:当消息消费出现异常时，没有被catch的话，会被重新丢回队列头部，重新消费 false:直接丢弃
        factory.setDefaultRequeueRejected(false);
        return factory;
    }

}

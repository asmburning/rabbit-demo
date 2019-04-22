package org.lxy.demo.rabbit.skt.component.mq;

import lombok.extern.slf4j.Slf4j;
import org.lxy.demo.rabbit.common.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@Component
@Slf4j
public class MqProducer {


    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendOrderFanoutMessage(MessageOrderFanout messageOrderFanout) {
        amqpTemplate.convertAndSend(MqConstants.OrderFanout.EXCHANGER_NAME, MqConstants.OrderFanout.ROUTING_KEY, messageOrderFanout);
        log.info("sendFanoutMessageSuccess");
    }

    public void sendOrderFanoutMessage2(MessageOrderFanout messageOrderFanout) {
        amqpTemplate.convertAndSend(MqConstants.OrderFanout.EXCHANGER_NAME, MqConstants.OrderFanout.QF_ORDER_CREATE, messageOrderFanout);
        log.info("sendFanoutMessageSuccess");
    }

    public void sendOrderDirectBindMessage(MessageOrderDirectBind messageOrderDirectBind) {
        amqpTemplate.convertAndSend(MqConstants.OrderDirect.EXCHANGER_NAME, MqConstants.OrderDirect.QD_ORDER_BIND, messageOrderDirectBind);
        log.info("sendOrderDirectBindMessage");
    }

    public void sendOrderDirectDeliveryMessage(MessageOrderDirectDelivery messageOrderDirectDelivery) {
        amqpTemplate.convertAndSend(MqConstants.OrderDirect.EXCHANGER_NAME, MqConstants.OrderDirect.QD_ORDER_DELIVERY, messageOrderDirectDelivery);
        log.info("sendOrderDirectBindMessage");
    }

    public void sendOrderDirectPayMessage(MessageOrderDirectDelivery messageOrderDirectDelivery) {
        amqpTemplate.convertAndSend(MqConstants.OrderDirect.EXCHANGER_NAME, MqConstants.OrderDirect.QD_ORDER_PAY, messageOrderDirectDelivery);
        log.info("sendOrderDirectBindMessage");
    }

    public void sendPayTopicMessage(String routingKey, MessageTopicPay messageTopicPay) {
        amqpTemplate.convertAndSend(MqConstants.PayTopic.EXCHANGER_NAME, routingKey, messageTopicPay);
        log.info("sendPayTopicMessage");
    }

}

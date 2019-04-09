package org.lxy.demo.rabbit.skt.component.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.lxy.demo.rabbit.common.MessageOrderDirectBind;
import org.lxy.demo.rabbit.common.MessageOrderDirectDelivery;
import org.lxy.demo.rabbit.common.MqConstants;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@Slf4j
@Component
public class OrderDirectMqListener {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderDirect.QD_ORDER_BIND, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderDirect.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "direct"),
                    key = MqConstants.OrderDirect.QD_ORDER_BIND))
    public void onBindMessage(@Payload MessageOrderDirectBind directBind, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onBindMessage receiver getMessage ");
        try {

            log.info("onBindMessage messageOrderFanout:{},deliveryTag:{}", directBind, deliveryTag);

        } catch (Exception e) {
            log.error("onBindMessage message:{},deliveryTag:{}", directBind, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderDirect.QD_ORDER_DELIVERY, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderDirect.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "direct"),
                    key = MqConstants.OrderDirect.QD_ORDER_DELIVERY))
    public void onDeliveryMessage(@Payload MessageOrderDirectDelivery directDelivery, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onDeliveryMessage receiver getMessage ");
        try {
            log.info("onDeliveryMessage messageOrderFanout:{},deliveryTag:{}", directDelivery, deliveryTag);

        } catch (Exception e) {
            log.error("onDeliveryMessage message:{},deliveryTag:{}", directDelivery, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

}

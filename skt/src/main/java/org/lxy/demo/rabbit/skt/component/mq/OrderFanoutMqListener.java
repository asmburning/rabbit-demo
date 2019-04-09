package org.lxy.demo.rabbit.skt.component.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.lxy.demo.rabbit.common.MessageOrderFanout;
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
public class OrderFanoutMqListener {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderFanout.QF_ORDER_CREATE, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderFanout.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "fanout"),
                    key = "rent-bike"))
    public void onCreateMessage(@Payload MessageOrderFanout messageOrderFanout, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onCreateMessage receiver getMessage ");
        try {

            log.info("onCreateMessage messageOrderFanout:{},deliveryTag:{}", messageOrderFanout, deliveryTag);

        } catch (Exception e) {
            log.error("onCreateMessage message:{},deliveryTag:{}", messageOrderFanout, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderFanout.QF_ORDER_CANCEL, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderFanout.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "fanout"),
                    key = "rent-bike"))
    public void onCancelMessage(@Payload MessageOrderFanout messageOrderFanout, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onCancelMessage receiver getMessage ");
        try {
            log.info("onCancelMessage messageOrderFanout:{},deliveryTag:{}", messageOrderFanout, deliveryTag);

        } catch (Exception e) {
            log.error("onCancelMessage message:{},deliveryTag:{}", messageOrderFanout, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderFanout.QF_ORDER_PAY_CALLBACK, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderFanout.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "fanout"),
                    key = "rent-bike"))
    public void onPayCallbackMessage(@Payload MessageOrderFanout messageOrderFanout, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onPayCallbackMessage receiver getMessage ");
        try {

            log.info("onPayCallbackMessage messageOrderFanout:{},deliveryTag:{}", messageOrderFanout, deliveryTag);

        } catch (Exception e) {
            log.error("onPayCallbackMessage message:{},deliveryTag:{}", messageOrderFanout, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }
}

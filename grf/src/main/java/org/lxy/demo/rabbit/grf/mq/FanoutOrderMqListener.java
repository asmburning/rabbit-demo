package org.lxy.demo.rabbit.grf.mq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @date 2019-04-09
 */
@Component
@Slf4j
public class FanoutOrderMqListener {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderFanout.QF_ORDER_PAY_CALLBACK, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderFanout.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "fanout"),
                    key = "rent-bike"))
    public void onPayCallbackMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onPayCallbackMessage receiver getMessage ");
        try {
            if (StringUtils.isBlank(message)) {
                return;
            }
            MessageOrderFanout messageOrderFanout = JSON.parseObject(message, MessageOrderFanout.class);
            log.info("onOrderPayCallbackMessage messageOrderFanout:{},deliveryTag:{}", messageOrderFanout, deliveryTag);

        } catch (Exception e) {
            log.error("onPayCallbackMessage message:{},deliveryTag:{}", message, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.OrderFanout.QF_ORDER_COMPLETE, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.OrderFanout.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "fanout"),
                    key = "rent-bike"))
    public void onComplete(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onComplete receiver getMessage ");
        try {
            if (StringUtils.isBlank(message)) {
                return;
            }
            MessageOrderFanout messageOrderFanout = JSON.parseObject(message, MessageOrderFanout.class);

            log.info("onComplete messageOrderFanout:{},deliveryTag:{}", messageOrderFanout, deliveryTag);

        } catch (Exception e) {
            log.error("onComplete message:{},deliveryTag:{}", message, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }
}

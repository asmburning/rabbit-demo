package org.lxy.demo.rabbit.skt.component.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.lxy.demo.rabbit.common.MessageTopicPay;
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
public class HeadersMqListener {

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.PayTopic.QT_PAY, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.PayTopic.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "topic"),
                    key = MqConstants.PayTopic.QTRK_PAY))
    public void onPayMessage(@Payload MessageTopicPay messageTopicPay, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onPayMessage receiver getMessage ");
        try {

            log.info("onPayMessage messageOrderFanout:{},deliveryTag:{}", messageTopicPay, deliveryTag);

        } catch (Exception e) {
            log.error("onPayMessage message:{},deliveryTag:{}", messageTopicPay, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

    @RabbitListener(containerFactory = "rabbitListenerContainerFactory", admin = "rabbitAdmin",
            bindings = @QueueBinding(value = @Queue(value = MqConstants.PayTopic.QT_PAY_COLLECT, durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = MqConstants.PayTopic.EXCHANGER_NAME, durable = "true", autoDelete = "false", internal = "false", type = "topic"),
                    key = MqConstants.PayTopic.QTRK_PAY_COLLECT))
    public void onCollectPayMessage(@Payload MessageTopicPay messageTopicPay, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws IOException {
        log.info("onCollectPayMessage receiver getMessage ");
        try {
            log.info("onCollectPayMessage messageOrderFanout:{},deliveryTag:{}", messageTopicPay, deliveryTag);

        } catch (Exception e) {
            log.error("onCollectPayMessage message:{},deliveryTag:{}", messageTopicPay, deliveryTag, e);
        } finally {
            channel.basicAck(deliveryTag, false);
        }
    }

}

package org.lxy.demo.rabbit.skt.component.mq;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.lxy.demo.rabbit.common.MessageOrderBuyCallback;
import org.lxy.demo.rabbit.common.MessageOrderFanout;
import org.lxy.demo.rabbit.common.MqConstants;
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
        amqpTemplate.convertAndSend(MqConstants.OrderFanout.EXCHANGER_NAME, MqConstants.OrderFanout.ROUTING_KEY, JSON.toJSONString(messageOrderFanout));
        log.info("sendFanoutMessageSuccess");
    }

    public void sendOrderBuyCallbackMessage(MessageOrderBuyCallback messageOrderBuyCallback) {

    }

}

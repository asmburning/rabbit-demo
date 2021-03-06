package org.lxy.demo.rabbit.skt.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.lxy.demo.rabbit.common.MessageOrderDirectBind;
import org.lxy.demo.rabbit.common.MessageOrderDirectDelivery;
import org.lxy.demo.rabbit.common.MessageOrderFanout;
import org.lxy.demo.rabbit.common.MessageTopicPay;
import org.lxy.demo.rabbit.skt.component.mq.MqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@RestController
public class TestMqController {

    @Autowired
    private MqProducer mqProducer;

    @RequestMapping("/sendMessage")
    public Object sendMessage() {
        mqProducer.sendOrderFanoutMessage(MessageOrderFanout.builder().orderId(RandomStringUtils.randomAlphanumeric(5)).status(1).amount(BigDecimal.ONE).build());
        return "ok";
    }

    @RequestMapping("/sendMessage2")
    public Object sendMessage2() {
        mqProducer.sendOrderFanoutMessage2(MessageOrderFanout.builder().orderId(RandomStringUtils.randomAlphanumeric(5)).status(1).amount(BigDecimal.ONE).build());
        return "ok";
    }

    @RequestMapping("/sendMessage3")
    public Object sendMessage3() {
        mqProducer.sendOrderDirectBindMessage(MessageOrderDirectBind.builder().orderId(RandomStringUtils.randomAlphanumeric(5)).amount(BigDecimal.ONE).build());
        return "ok";
    }

    @RequestMapping("/sendMessage4")
    public Object sendMessage4() {
        mqProducer.sendOrderDirectDeliveryMessage(MessageOrderDirectDelivery.builder().orderId(RandomStringUtils.randomAlphanumeric(5)).amount(BigDecimal.ONE).build());
        return "ok";
    }

    @RequestMapping("/sendOrderDirectPayMessage")
    public Object sendOrderDirectPayMessage() {
        mqProducer.sendOrderDirectPayMessage(MessageOrderDirectDelivery.builder().orderId(RandomStringUtils.randomAlphanumeric(5)).amount(BigDecimal.ONE).build());
        return "ok";
    }

    @RequestMapping("/sendTopicMessage")
    public Object sendTopicMessage(@RequestParam String routingKey) {
        mqProducer.sendPayTopicMessage(routingKey,
                MessageTopicPay.builder().orderId(RandomStringUtils.randomAlphanumeric(5)).build());
        return "ok";
    }
}

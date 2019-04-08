package org.lxy.demo.rabbit.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
public interface MqConstants {

    interface OrderFanout {

        String EXCHANGER_NAME = "EXCHANGER_FANOUT_ORDER";

        String ROUTING_KEY = "";

        String QF_ORDER_CREATE = "QF_ORDER_CREATE";

        String QF_ORDER_CANCEL = "QF_ORDER_CANCEL";

        String QF_ORDER_PAY_CALLBACK = "QF_ORDER_PAY_CALLBACK";

        Set<String> QUEUE_SET = Sets.newHashSet(QF_ORDER_CREATE, QF_ORDER_CANCEL, QF_ORDER_PAY_CALLBACK);
    }

    interface OrderDirect {

        String EXCHANGER_NAME = "EXCHANGER_DIRECT_ORDER";

        String QD_ORDER_BIND = "QD_ORDER_BIND";

        String QD_ORDER_DELIVERY = "QD_ORDER_DELIVERY";

    }

}

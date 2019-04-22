package org.lxy.demo.rabbit.common;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
public interface MqConstants {

    /**
     * broadcast message to all binding queues ignore routingKey
     */
    interface OrderFanout {

        String EXCHANGER_NAME = "EXCHANGER_FANOUT_ORDER";

        String ROUTING_KEY = "";

        String QF_ORDER_CREATE = "QF_ORDER_CREATE";

        String QF_ORDER_CANCEL = "QF_ORDER_CANCEL";

        String QF_ORDER_PAY_CALLBACK = "QF_ORDER_PAY_CALLBACK";

        String QF_ORDER_COMPLETE = "QF_ORDER_COMPLETE";

        Set<String> QUEUE_SET = Sets.newHashSet(QF_ORDER_CREATE, QF_ORDER_CANCEL, QF_ORDER_PAY_CALLBACK, QF_ORDER_COMPLETE);
    }

    /**
     * sending message to the special queue based on the binding routing key
     */
    interface OrderDirect {

        String EXCHANGER_NAME = "EXCHANGER_DIRECT_ORDER";

        String QD_ORDER_BIND = "QD_ORDER_BIND";
        String QD_ORDER_DELIVERY = "QD_ORDER_DELIVERY";
        String QD_ORDER_PAY = "QD_ORDER_PAY";
        String QD_USER_PAY_DIRECT = "QD_USER_PAY_DIRECT";


        @AllArgsConstructor
        @Getter
        enum OrderDirectQueue{

            QD_ORDER_BIND("QD_ORDER_BIND", "QD_ORDER_BIND", "订单绑定"),
            QD_ORDER_DELIVERY("QD_ORDER_DELIVERY", "QD_ORDER_DELIVERY", "订单配送"),
            QD_ORDER_PAY("QD_ORDER_PAY", "QD_ORDER_PAY", "订单支付成功"),
            QD_USER_PAY_DIRECT("QD_USER_PAY_DIRECT", "QD_ORDER_PAY", "订单支付成功")
            ;

            private String queueName;
            private String routingKey;
            private String msg;
        }

    }

    /**
     * just like aspect in spring
     * a queue in topic concerns the special aspect , eg pay callback may happen in different business line
     * a queue concerns pay may
     */
    interface PayTopic {

        String EXCHANGER_NAME = "EXCHANGER_TOPIC_ORDER";

        String QT_PAY_COLLECT = "QT_PAY_COLLECT";
        String QTRK_PAY_COLLECT = "#.collect.pay.#";

        String QT_PAY = "QT_PAY";
        String QTRK_PAY = "#.pay.#";

        String RK_RENT_ORDER_PAY_CALLBACK = "rent.order.pay.callback";

        String RK_RENT_COLLECT_PAY_CALLBACK = "rent.collect.pay.callback";

        String RK_RENT_DELIVERY_PAY_CALLBACK = "rent.delivery.pay.callback";

        String RK_RENT_COMPENSATION_PAY_CALLBACK = "rent.compensation.pay.callback";

        String RK_RENT_FINE_PAY_CALLBACK = "rent.fine.pay.callback";


    }


    interface OrderHeaders {
        String EXCHANGER_NAME = "EXCHANGER_HEADERS_ORDER";

        String QH_ORDER = "QH_ORDER";

        String QH_ORDER_KEY_TAG = "x-tag";

    }

}

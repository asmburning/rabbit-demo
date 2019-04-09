package org.lxy.demo.rabbit.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuxinyi
 * @date 2019-04-09
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTopicPay {
    private String payChannel;
    private String orderId;
    private String orderType;
}

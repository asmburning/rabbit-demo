package org.lxy.demo.rabbit.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author liuxinyi
 * @date 2019-04-08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageOrderDirectDelivery {

    private String orderId;
    private BigDecimal amount;
}

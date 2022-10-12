package com.ruoyi.property.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.enums.FeeTradeState;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用账单（用户端结算）
 */
@Data
public class FeeTrade extends BaseEntity {
    public static final String BIZ_CHANNEL_CONTRACT_DEPOSIT = "CONTRACT_DEPOSIT";
    public static final String BIZ_CHANNEL_WEIXIN_PAY = "WEIXIN_PAY";

    private String id;
    private String ownerId;
    private String no;
    private String subject;
    private String description;
    private BigDecimal price;
    private BigDecimal count;
    private BigDecimal amount;
    private FeeTradeState state;
    private Date paidAt;
    private BigDecimal paidAmount;
    private Date refundedAt;
    private BigDecimal refundedAmount;
    private String bizChannel;
    private String bizTradeNo;
    private String outScope;
    private String outId;
    private String contractId;
}

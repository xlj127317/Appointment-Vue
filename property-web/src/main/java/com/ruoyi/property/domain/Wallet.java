package com.ruoyi.property.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 在线钱包
 */
@Data
public class Wallet extends BaseEntity {
    /**
     * 用户id
     */
    private String ownerId;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 已冻结
     */
    private boolean frozen;

    /**
     * 上次对账时间
     */
    private Date checkedAt;
}

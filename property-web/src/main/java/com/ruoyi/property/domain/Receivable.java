package com.ruoyi.property.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 应收管理表
 */
@ApiModel(value = "应收管理表")
@Schema
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Receivable extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 关联合同单id
     */
    @ApiModelProperty(value = "关联合同单id")
    @Schema(description = "关联合同单id")
    private String contractId;

    /**
     * 关联业主id
     */
    @ApiModelProperty(value = "关联业主id")
    @Schema(description = "关联业主id")
    private String ownerId;

    /**
     * 款项类别id
     */
    @ApiModelProperty(value = "款项类别id")
    @Schema(description = "款项类别id")
    private String paymentId;

    /**
     * 款项名称
     */
    @ApiModelProperty(value = "款项名称")
    @Schema(description = "款项名称")
    private String paymentName;

    /**
     * 款项内容
     */
    @ApiModelProperty(value = "款项内容")
    @Schema(description = "款项内容")
    private String paymentContent;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    @Schema(description = "应收金额")
    private String receivableMoney;

    /**
     * 款项年月
     */
    @ApiModelProperty(value = "款项年月")
    @Schema(description = "款项年月")
    private Date paymentDate;

    /**
     * 预收状态，默认0,0：无预收抵扣，1：已抵扣
     */
    @ApiModelProperty(value = "预收状态，默认0,0：无预收抵扣，1：已抵扣")
    @Schema(description = "预收状态，默认0,0：无预收抵扣，1：已抵扣")
    private Integer advanceStatus;

    /**
     * 预收抵扣金额
     */
    @ApiModelProperty(value = "预收抵扣金额")
    @Schema(description = "预收抵扣金额")
    private String deductMoney;

    /**
     * 抵扣后应收金额
     */
    @ApiModelProperty(value = "抵扣后应收金额")
    @Schema(description = "抵扣后应收金额")
    private String deductAfterMoney;

    /**
     * 抵扣时间
     */
    @ApiModelProperty(value = "抵扣时间")
    @Schema(description = "抵扣时间")
    private Date deductDate;

    /**
     * 实收状态 默认0，0：未实收，1：已实收
     */
    @ApiModelProperty(value = "实收状态 默认0，0：未实收，1：已实收")
    @Schema(description = "实收状态 默认0，0：未实收，1：已实收")
    private Integer actuallyStatus;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    @Schema(description = "实收金额")
    private String actuallyMoney;

    /**
     * 实收时间
     */
    @ApiModelProperty(value = "实收时间")
    @Schema(description = "实收时间")
    private Date actuallyDate;

    /**
     * 应收截止时间
     */
    @ApiModelProperty(value = "应收截止时间")
    @Schema(description = "应收截止时间")
    private Date stopTime;

    /**
     * 是否欠收；默认0，0：未欠收，1：欠收
     */
    @ApiModelProperty(value = "是否欠收；默认0，0：未欠收，1：欠收")
    @Schema(description = "是否欠收；默认0，0：未欠收，1：欠收")
    private Integer arrearsStatus;

    /**
     * 欠收金额
     */
    @ApiModelProperty(value = "欠收金额")
    @Schema(description = "欠收金额")
    private String arrearsMoney;

    /**
     * 补缴时间
     */
    @ApiModelProperty(value = "补缴时间")
    @Schema(description = "补缴时间")
    private Date repairDate;

    /**
     * 付款方式 0：直接支付，1：押金抵扣
     */
    @ApiModelProperty(value = "付款方式 0：直接支付，1：押金抵扣")
    @Schema(description = "付款方式 0：直接支付，1：押金抵扣")
    private Integer paymentType;

    /**
     * 是否退款；默认0，0：未退款，1：已退款
     */
    @ApiModelProperty(value = "是否退款；默认0，0：未退款，1：已退款")
    @Schema(description = "是否退款；默认0，0：未退款，1：已退款")
    private Integer refundStatus;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    @Schema(description = "创建人id")
    private String createId;

    /**
     * 状态,0 未删除 1已删除
     */
    @ApiModelProperty(value = "状态,0 未删除 1已删除")
    @Schema(description = "状态,0 未删除 1已删除")
    private Integer isFlag;
}
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
    * 预收管理表
    */
@ApiModel(value="预收管理表")
@Schema
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class Advance extends BaseEntity {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    @Schema(description="主键")
    private String id;

    /**
    * 关联合同单id
    */
    @ApiModelProperty(value="关联合同单id")
    @Schema(description="关联合同单id")
    private String contractId;

    /**
    * 关联业主id
    */
    @ApiModelProperty(value="关联业主id")
    @Schema(description="关联业主id")
    private String ownerId;

    /**
    * 款项类别
    */
    @ApiModelProperty(value="款项类别")
    @Schema(description="款项类别")
    private String paymentType;

    /**
    * 款项名称
    */
    @ApiModelProperty(value="款项名称")
    @Schema(description="款项名称")
    private String paymentName;

    /**
    * 款项内容
    */
    @ApiModelProperty(value="款项内容")
    @Schema(description="款项内容")
    private String paymentContent;

    /**
    * 预收金额
    */
    @ApiModelProperty(value="预收金额")
    @Schema(description="预收金额")
    private String advanceMoney;

    /**
    * 支付方式
    */
    @ApiModelProperty(value="支付方式")
    @Schema(description="支付方式")
    private String payType;

    /**
    * 预收款项年月
    */
    @ApiModelProperty(value="预收款项年月")
    @Schema(description="预收款项年月")
    private Date advanceDate;

    /**
    * 抵扣状态；0：未抵扣 1：已抵扣
    */
    @ApiModelProperty(value="抵扣状态；0：未抵扣 1：已抵扣")
    @Schema(description="抵扣状态；0：未抵扣 1：已抵扣")
    private Integer deductStatus;

    /**
    * 抵扣时间
    */
    @ApiModelProperty(value="抵扣时间")
    @Schema(description="抵扣时间")
    private Date deductTime;

    /**
    * 创建人id
    */
    @ApiModelProperty(value="创建人id")
    @Schema(description="创建人id")
    private String createId;

    /**
    * 状态,0 未删除 1已删除
    */
    @ApiModelProperty(value="状态,0 未删除 1已删除")
    @Schema(description="状态,0 未删除 1已删除")
    private Integer isFlag;
}
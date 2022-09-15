package com.ruoyi.backstage.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 实收管理表
 *
 * @author PG
 */
@ApiModel(value = "实收管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actually extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 实收类型id
     */
    @ApiModelProperty(value = "实收类型id")
    @Schema(description = "实收类型id")
    private String typeId;

    /**
     * 实收名称
     */
    @ApiModelProperty(value = "实收名称")
    @Schema(description = "实收名称")
    private String actuallyName;

    /**
     * 实收内容
     */
    @ApiModelProperty(value = "实收内容")
    @Schema(description = "实收内容")
    private String actuallyContent;

    /**
     * 实收金额
     */
    @ApiModelProperty(value = "实收金额")
    @Schema(description = "实收金额")
    private String actuallyMoney;

    /**
     * 款项年月
     */
    @ApiModelProperty(value = "款项年月")
    @Schema(description = "款项年月")
    private Date paymentTime;

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
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    @Schema(description = "创建人id")
    private String createId;

    /**
     * 预收状态，默认0,0：无预收抵扣，1：已抵扣
     */
    @ApiModelProperty(value = "预收状态，默认0,0：无预收抵扣，1：已抵扣")
    @Schema(description = "预收状态，默认0,0：无预收抵扣，1：已抵扣")
    private Integer advanceStatus;

    /**
     * 实收状态，默认0,0：未实收，1：已实收
     */
    @ApiModelProperty(value = "实收状态，默认0,0：未实收，1：已实收")
    @Schema(description = "实收状态，默认0,0：未实收，1：已实收")
    private Integer actuallyStatus;

    /**
     * 欠收状态，默认0,0：否，1：是，2：已补缴
     */
    @ApiModelProperty(value = "欠收状态，默认0,0：否，1：是，2：已补缴")
    @Schema(description = "欠收状态，默认0,0：否，1：是，2：已补缴")
    private Integer isArrears;

    /**
     * 付款方式，默认0,0：直接付款，1：押金抵扣
     */
    @ApiModelProperty(value = "付款方式，默认0,0：直接付款，1：押金抵扣")
    @Schema(description = "付款方式，默认0,0：直接付款，1：押金抵扣")
    private Integer paymentType;

    /**
     * 实收时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "实收时间")
    @Schema(description = "实收时间")
    private Date actuallyTime;

    /**
     * 状态,0 未删除 1已删除
     */
    @ApiModelProperty(value = "状态,0 未删除 1已删除")
    @Schema(description = "状态,0 未删除 1已删除")
    private Integer isFlag;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    @ApiModelProperty(value = "创建时间")
    @Schema(description = "创建时间")
    private Date createTime;
}
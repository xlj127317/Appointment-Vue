package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 应收管理表
 */
@ApiModel(value = "应收管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receivable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 应收类型id
     */
    @ApiModelProperty(value = "应收类型id")
    @Schema(description = "应收类型id")
    private String typeId;

    /**
     * 应收名称
     */
    @ApiModelProperty(value = "应收名称")
    @Schema(description = "应收名称")
    private String receivableName;

    /**
     * 应收内容
     */
    @ApiModelProperty(value = "应收内容")
    @Schema(description = "应收内容")
    private String receivableContent;

    /**
     * 应收金额
     */
    @ApiModelProperty(value = "应收金额")
    @Schema(description = "应收金额")
    private String receivableMoney;

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
     * 截止时间
     */
    @ApiModelProperty(value = "截止时间")
    @Schema(description = "截止时间")
    private Date stopTime;

    /**
     * 应收单状态，0：未过期，1：欠收
     */
    @ApiModelProperty(value = "应收单状态，0：未过期，1：欠收")
    @Schema(description = "应收单状态，0：未过期，1：欠收")
    private Integer receivableStatus;

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

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Schema(description = "创建时间")
    private Date createTime;
}
package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 票据管理表
 */
@ApiModel(value = "票据管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 票据类别id 应收、预收、实收、退款
     */
    @ApiModelProperty(value = "票据类别id 应收、预收、实收、退款")
    @Schema(description = "票据类别id 应收、预收、实收、退款")
    private String typeId;

    /**
     * 票据名称
     */
    @ApiModelProperty(value = "票据名称")
    @Schema(description = "票据名称")
    private String billName;

    /**
     * 票据内容
     */
    @ApiModelProperty(value = "票据内容")
    @Schema(description = "票据内容")
    private String billContent;

    /**
     * 票据金额
     */
    @ApiModelProperty(value = "票据金额")
    @Schema(description = "票据金额")
    private String billMoney;

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
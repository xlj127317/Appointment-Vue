package com.ruoyi.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
public class Actually {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 实收类型id
     */
    @NotBlank(message = "类型id不能为空")
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
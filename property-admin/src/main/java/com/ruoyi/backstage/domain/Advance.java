package com.ruoyi.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 预收管理表
 */
@ApiModel(value = "预收管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advance {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 预收类型id
     */
    @ApiModelProperty(value = "预收类型id")
    @Schema(description = "预收类型id")
    private String typeId;

    /**
     * 预收名称
     */
    @ApiModelProperty(value = "预收名称")
    @Schema(description = "预收名称")
    private String advanceName;

    /**
     * 预收内容
     */
    @ApiModelProperty(value = "预收内容")
    @Schema(description = "预收内容")
    private String advanceContent;

    /**
     * 预收金额
     */
    @ApiModelProperty(value = "预收金额")
    @Schema(description = "预收金额")
    private String advanceMoney;

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
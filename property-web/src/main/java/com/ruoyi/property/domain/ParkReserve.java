package com.ruoyi.property.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 园区资源预约
 */
@ApiModel(value = "园区资源预约")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkReserve extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 园区资源id
     */
    @ApiModelProperty(value = "园区资源id")
    @Schema(description = "园区资源id")
    private String resId;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @Schema(description = "资源名称")
    private String resName;

    /**
     * 预约起止时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "预约起止时间")
    @Schema(description = "预约起止时间")
    private Date statTime;

    /**
     * 预约结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "预约结束时间")
    @Schema(description = "预约结束时间")
    private Date stopTime;

    /**
     * 原因
     */
    @ApiModelProperty(value = "原因")
    @Schema(description = "原因")
    private String reason;

    /**
     * 审核状态.0 待审核，1 审核通过 2 已过期
     */
    @ApiModelProperty(value = "审核状态.0 待审核，1 审核通过 2 已过期")
    @Schema(description = "审核状态.0 待审核，1 审核通过 2 已过期")
    private Integer auditStatus;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @Schema(description = "审核时间")
    private Date auditDate;

    /**
     * 审核人id
     */
    @ApiModelProperty(value = "审核人id")
    @Schema(description = "审核人id")
    private String auditId;

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
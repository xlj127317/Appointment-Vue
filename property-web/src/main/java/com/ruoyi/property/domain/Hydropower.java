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
 * 水电统计
 */
@ApiModel(value = "水电统计")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hydropower extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 房屋id
     */
    @ApiModelProperty(value = "房屋id")
    @Schema(description = "房屋id")
    private String houseId;

    /**
     * 用水量
     */
    @ApiModelProperty(value = "用水量")
    @Schema(description = "用水量")
    private String waterAmount;

    /**
     * 用电量
     */
    @ApiModelProperty(value = "用电量")
    @Schema(description = "用电量")
    private String electricity;

    /**
     * 统计时间 yyyy-MM
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "统计时间 yyyy-MM")
    @Schema(description = "统计时间 yyyy-MM")
    private Date countTime;

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
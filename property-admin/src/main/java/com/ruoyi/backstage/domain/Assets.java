package com.ruoyi.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产管理表
 *
 * @author wind
 */
@ApiModel(value = "资产管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assets {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 资产类别
     */
    @ApiModelProperty(value = "资产类别")
    @Schema(description = "资产类别")
    private String typeId;

    /**
     * 资产名称
     */
    @ApiModelProperty(value = "资产名称")
    @Schema(description = "资产名称")
    private String assetsName;

    /**
     * 默认0，0：正常，1：报废
     */
    @ApiModelProperty(value = "默认0，0：正常，1：报废")
    @Schema(description = "默认0，0：正常，1：报废")
    private Integer assetsStatus;

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
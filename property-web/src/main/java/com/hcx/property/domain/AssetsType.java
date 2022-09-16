package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 资产类别管理表
 *
 * @author wind
 */
@ApiModel(value = "资产类别管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetsType {
    /**
     * 资产类别主键
     */
    @ApiModelProperty(value = "资产类别主键")
    @Schema(description = "资产类别主键")
    private String id;

    /**
     * 资产类别
     */
    @ApiModelProperty(value = "资产类别")
    @Schema(description = "资产类别")
    private String assetsType;

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
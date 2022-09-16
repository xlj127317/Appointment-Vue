package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 租售管理表
 */
@ApiModel(value = "租售管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentSell {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 关联房屋id
     */
    @ApiModelProperty(value = "关联房屋id")
    @Schema(description = "关联房屋id")
    private String housesId;

    /**
     * 所属业主id
     */
    @ApiModelProperty(value = "所属业主id")
    @Schema(description = "所属业主id")
    private String ownerId;

    /**
     * 销售时间
     */
    @ApiModelProperty(value = "销售时间")
    @Schema(description = "销售时间")
    private String sellTime;

    /**
     * 销售金额
     */
    @ApiModelProperty(value = "销售金额")
    @Schema(description = "销售金额")
    private String sellMoney;

    /**
     * 销售方
     */
    @ApiModelProperty(value = "销售方")
    @Schema(description = "销售方")
    private String seller;

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
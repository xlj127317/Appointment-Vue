package com.ruoyi.property.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 租售管理表
 */
@ApiModel(value = "租售管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentSell extends BaseEntity {
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
     * 租售类型id
     */
    @ApiModelProperty(value = "租售类型id")
    @Schema(description = "租售类型id")
    private String sellId;

    /**
     * 所属业主id
     */
    @ApiModelProperty(value = "所属业主id")
    @Schema(description = "所属业主id")
    private String ownerId;

    /**
     * 业主姓名
     */
    @ApiModelProperty(value = "业主姓名")
    @Schema(description = "业主姓名")
    private String ownerName;

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
     * 租售状态；默认0；0：有效；1：失效
     */
    @ApiModelProperty(value = "租售状态；默认0；0：有效；1：失效")
    @Schema(description = "租售状态；默认0；0：有效；1：失效")
    private Integer rentStatus;

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
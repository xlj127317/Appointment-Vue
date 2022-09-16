package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 自动冲抵数据表
 */
@ApiModel(value = "自动冲抵数据表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChargeOffset {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 抵充类别id（押金抵充）
     */
    @ApiModelProperty(value = "抵充类别id（押金抵充）")
    @Schema(description = "抵充类别id（押金抵充）")
    private String typeId;

    /**
     * 抵充名称
     */
    @ApiModelProperty(value = "抵充名称")
    @Schema(description = "抵充名称")
    private String chargeName;

    /**
     * 抵充内容
     */
    @ApiModelProperty(value = "抵充内容")
    @Schema(description = "抵充内容")
    private String chargeContent;

    /**
     * 抵充金额 （手输）
     */
    @ApiModelProperty(value = "抵充金额 （手输）")
    @Schema(description = "抵充金额 （手输）")
    private String chargeMoney;

    /**
     * 关联合同单id
     */
    @ApiModelProperty(value = "关联合同单id")
    @Schema(description = "关联合同单id")
    private String contractId;

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
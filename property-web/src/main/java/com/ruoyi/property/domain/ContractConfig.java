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
 * 合同配置表
 */
@ApiModel(value = "合同配置表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 收费项目
     */
    @ApiModelProperty(value = "收费项目")
    @Schema(description = "收费项目")
    private String tollProject;

    /**
     * 收费金额（元/月）
     */
    @ApiModelProperty(value = "收费金额（元/月）")
    @Schema(description = "收费金额（元/月）")
    private String tollMoney;

    /**
     * 缴费标准
     */
    @ApiModelProperty(value = "缴费标准")
    @Schema(description = "缴费标准")
    private String payStand;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Schema(description = "备注")
    private String remark;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @Schema(description = "创建人")
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
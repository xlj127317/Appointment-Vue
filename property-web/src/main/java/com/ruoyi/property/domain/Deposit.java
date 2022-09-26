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
 * 押金表
 */
@ApiModel(value = "押金表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deposit extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 业主id
     */
    @ApiModelProperty(value = "业主id")
    @Schema(description = "业主id")
    private String userId;

    /**
     * 合同单id
     */
    @ApiModelProperty(value = "合同单id")
    @Schema(description = "合同单id")
    private String contractId;

    /**
     * 押金总额
     */
    @ApiModelProperty(value = "押金总额")
    @Schema(description = "押金总额")
    private String depositMoney;

    /**
     * 最后更新人
     */
    @ApiModelProperty(value = "最后更新人")
    @Schema(description = "最后更新人")
    private String updateUser;

    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    @Schema(description = "最后更新时间")
    private Date updateTime;

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
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
 * 合同管理表
 */
@ApiModel(value = "合同管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 合同类型id
     */
    @ApiModelProperty(value = "合同类型id")
    @Schema(description = "合同类型id")
    private String typeId;

    /**
     * 甲方 关联业主id
     */
    @ApiModelProperty(value = "甲方 关联业主id")
    @Schema(description = "甲方 关联业主id")
    private String partyA;

    /**
     * 甲方（是业主）关联业主id
     */
    @ApiModelProperty(value = "甲方（是业主）关联业主id")
    @Schema(description = "甲方（是业主）关联业主id")
    private String ownerId;

    /**
     * 乙方
     */
    @ApiModelProperty(value = "乙方")
    @Schema(description = "乙方")
    private String partyB;

    /**
     * 合同金额
     */
    @ApiModelProperty(value = "合同金额")
    @Schema(description = "合同金额")
    private String contractMoney;

    /**
     * 合同状态
     */
    @ApiModelProperty(value = "合同状态")
    @Schema(description = "合同状态")
    private Integer contractStatus;

    /**
     * 厂房面积 平方米 m2
     */
    @ApiModelProperty(value = "厂房面积 平方米 m2")
    @Schema(description = "厂房面积 平方米 m2")
    private String area;

    /**
     * 押金金额
     */
    @ApiModelProperty(value = "押金金额")
    @Schema(description = "押金金额")
    private String deposit;

    /**
     * 附件地址
     */
    @ApiModelProperty(value = "附件地址")
    @Schema(description = "附件地址")
    private String fileSrc;

    /**
     * 合同配置id
     */
    @ApiModelProperty(value = "合同配置id")
    @Schema(description = "合同配置id")
    private String configureId;

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
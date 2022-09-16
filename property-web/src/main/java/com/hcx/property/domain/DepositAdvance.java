package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 押金转预交数据表
 *
 * @author wind
 */
@ApiModel(value = "押金转预交数据表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepositAdvance {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @Schema(description = "用户id")
    private String userId;

    /**
     * 转预缴金额
     */
    @ApiModelProperty(value = "转预缴金额")
    @Schema(description = "转预缴金额")
    private String cMoney;

    /**
     * 关联应收id
     */
    @ApiModelProperty(value = "关联应收id")
    @Schema(description = "关联应收id")
    private String receivableId;

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
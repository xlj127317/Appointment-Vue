package com.ruoyi.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 退款管理表
 */
@ApiModel(value = "退款管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Refund {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 退款类型id
     */
    @ApiModelProperty(value = "退款类型id")
    @Schema(description = "退款类型id")
    private String typeId;

    /**
     * 退款名称
     */
    @ApiModelProperty(value = "退款名称")
    @Schema(description = "退款名称")
    private String refundName;

    /**
     * 退款内容
     */
    @ApiModelProperty(value = "退款内容")
    @Schema(description = "退款内容")
    private String refundContent;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @Schema(description = "退款金额")
    private String refundMoney;

    /**
     * 关联合同单id
     */
    @ApiModelProperty(value = "关联合同单id")
    @Schema(description = "关联合同单id")
    private String contractId;

    /**
     * 关联业主id
     */
    @ApiModelProperty(value = "关联业主id")
    @Schema(description = "关联业主id")
    private String ownerId;

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
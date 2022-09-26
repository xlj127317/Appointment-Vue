package com.ruoyi.property.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 工单管理表
 */
@ApiModel(value = "工单管理表")
@Schema
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Report extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 工单类别id
     */
    @ApiModelProperty(value = "工单类别id")
    @Schema(description = "工单类别id")
    private Integer typeId;

    /**
     * 工单内容
     */
    @ApiModelProperty(value = "工单内容")
    @Schema(description = "工单内容")
    private String reportContent;

    /**
     * 审批人
     */
    @ApiModelProperty(value = "审批人")
    @Schema(description = "审批人")
    private String auditUserId;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    @Schema(description = "审核状态")
    private Integer auditStatus;

    /**
     * 审批内容
     */
    @ApiModelProperty(value = "审批内容")
    @Schema(description = "审批内容")
    private String auditContent;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @Schema(description = "审核时间")
    private Date auditTime;

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
}
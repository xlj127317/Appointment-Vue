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
 * 用工任务表
 */
@ApiModel(value = "用工任务表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerTask extends BaseEntity {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @Schema(description = "id")
    private String id;

    /**
     * 接收人
     */
    @ApiModelProperty(value = "接收人")
    @Schema(description = "接收人")
    private String receiver;

    /**
     * 任务内容
     */
    @ApiModelProperty(value = "任务内容")
    @Schema(description = "任务内容")
    private String taskContent;

    /**
     * 完成状态;0：待完成；1已完成
     */
    @ApiModelProperty(value = "完成状态;0：待完成；1已完成")
    @Schema(description = "完成状态;0：待完成；1已完成")
    private Integer completeStatus;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @Schema(description = "完成时间")
    private Date completeTime;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    @Schema(description = "创建人id")
    private String createId;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    @Schema(description = "创建人姓名")
    private String createName;

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
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
 * 用工管理表
 */
@ApiModel(value = "用工管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @Schema(description = "姓名")
    private String username;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 工号id
     */
    @ApiModelProperty(value = "工号id")
    @Schema(description = "工号id")
    private String jobNum;

    /**
     * 推送状态 默认0 0为未推送 1，已推送，2推送失败
     */
    @ApiModelProperty(value = "推送状态 默认0 0为未推送 1，已推送，2推送失败")
    @Schema(description = "推送状态 默认0 0为未推送 1，已推送，2推送失败")
    private Integer pushStatus;

    /**
     * 完成状态 默认0 0，未完成 1，已完成
     */
    @ApiModelProperty(value = "完成状态 默认0 0，未完成 1，已完成")
    @Schema(description = "完成状态 默认0 0，未完成 1，已完成")
    private Integer finishStatus;

    /**
     * 完成时间
     */
    @ApiModelProperty(value = "完成时间")
    @Schema(description = "完成时间")
    private Date finishDate;

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
package com.ruoyi.property.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 * 物业报修申请表
 */
@ApiModel(value = "物业报修申请表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Repair extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 报修内容
     */
    @NotNull(message = "报修内容不能为空")
    @ApiModelProperty(value = "报修内容")
    @Schema(description = "报修内容")
    private String repairContent;

    /**
     * 图片路径
     */
    @ApiModelProperty(value = "图片路径")
    @Schema(description = "图片路径")
    private String imgSrc;

    /**
     * 申请时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Future(message = "申请时间不能早于当前时间")
    @ApiModelProperty(value = "申请时间")
    @Schema(description = "申请时间")
    private Date applicantTime;

    /**
     * 审核状态，默认0，0为未审核，1为已审核
     */
    @ApiModelProperty(value = "审核状态，默认0，0为未审核，1为已审核")
    @Schema(description = "审核状态，默认0，0为未审核，1为已审核")
    private Integer auditStatus;

    /**
     * 审核人id
     */
    @ApiModelProperty(value = "审核人id")
    @Schema(description = "审核人id")
    private String auditId;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    @Schema(description = "创建时间")
    private Date createTime;
}
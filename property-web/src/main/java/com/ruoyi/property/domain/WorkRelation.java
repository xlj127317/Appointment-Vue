package com.ruoyi.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 用工任务中间关联表
    */
@ApiModel(value="用工任务中间关联表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkRelation {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    @Schema(description="主键")
    private String workId;

    /**
    * 任务表id
    */
    @ApiModelProperty(value="任务表id")
    @Schema(description="任务表id")
    private String taskId;
}
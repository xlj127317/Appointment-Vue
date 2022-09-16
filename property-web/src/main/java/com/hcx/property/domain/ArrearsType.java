package com.hcx.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 欠收类型表
 */
@ApiModel(value = "欠收类型表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrearsType {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 欠收类型名称
     */
    @ApiModelProperty(value = "欠收类型名称")
    @Schema(description = "欠收类型名称")
    private String typeName;

    /**
     * 状态,0 未删除 1已删除
     */
    @ApiModelProperty(value = "状态,0 未删除 1已删除")
    @Schema(description = "状态,0 未删除 1已删除")
    private Integer isFlag;
}
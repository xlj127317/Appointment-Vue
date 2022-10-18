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
 * 园区资源
 */
@ApiModel(value = "园区资源")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkResource extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    @Schema(description = "资源名称")
    private String resourceName;

    /**
     * 资源状态，0 为未启用 1 已启用
     */
    @ApiModelProperty(value = "资源状态，0 为未启用 1 已启用")
    @Schema(description = "资源状态，0 为未启用 1 已启用")
    private Integer resStatus;

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
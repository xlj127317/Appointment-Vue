package com.ruoyi.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 房屋管理表
 */
@ApiModel(value = "房屋管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Houses {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 房屋编号
     */
    @ApiModelProperty(value = "房屋编号")
    @Schema(description = "房屋编号")
    private String housesNo;

    /**
     * 所属业主id
     */
    @ApiModelProperty(value = "所属业主id")
    @Schema(description = "所属业主id")
    private String ownerId;

    /**
     * 房屋面积，单位m2
     */
    @ApiModelProperty(value = "房屋面积，单位m2")
    @Schema(description = "房屋面积，单位m2")
    private String area;

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
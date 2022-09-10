package com.ruoyi.backstage.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业主管理表
 *
 * @author wind
 */
@ApiModel(value = "业主管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
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
     * 性别，0：男，1：女，2：未知
     */
    @ApiModelProperty(value = "性别，0：男，1：女，2：未知")
    @Schema(description = "性别，0：男，1：女，2：未知")
    private Integer gender;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    @Schema(description = "身份证号")
    private String identity;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    @Schema(description = "电话")
    private String phone;

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
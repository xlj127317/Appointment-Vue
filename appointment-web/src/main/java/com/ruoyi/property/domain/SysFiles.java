package com.ruoyi.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传
 */
@ApiModel(value = "文件上传")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysFiles {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @Schema(description = "id")
    private String id;

    /**
     * URL地址
     */
    @ApiModelProperty(value = "URL地址")
    @Schema(description = "URL地址")
    private String url;

    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    @Schema(description = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "文件路径")
    @Schema(description = "")
    private String filePath;

    /**
     * 上传人id
     */
    @ApiModelProperty(value = "上传人id")
    @Schema(description = "上传人id")
    private String createId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Schema(description = "创建时间")
    private Date createDate;

    /**
     * 删除标识位
     */
    @ApiModelProperty(value = "删除标识位")
    @Schema(description = "删除标识位")
    private Integer isDelete;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @Schema(description = "更新时间")
    private Date updateTime;
}
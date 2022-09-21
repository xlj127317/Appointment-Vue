package com.ruoyi.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资产类别管理表
 */
@ApiModel(value = "资产类别管理表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetsType {
    public static final String COL_ID = "id";
    public static final String COL_ASSETS_TYPE = "assets_type";
    public static final String COL_CREATE_ID = "create_id";
    public static final String COL_TYPE_STATUS = "type_status";
    public static final String COL_IS_FLAG = "is_flag";
    public static final String COL_CREATE_TIME = "create_time";
    /**
     * 资产类别主键
     */
    @ApiModelProperty(value = "资产类别主键")
    @Schema(description = "资产类别主键")
    private String id;

    /**
     * 资产类别
     */
    @ApiModelProperty(value = "资产类别")
    @Schema(description = "资产类别")
    private String assetsType;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    @Schema(description = "创建人id")
    private String createId;

    /**
     * 类型状态；0：开启，1：关闭，默认：0
     */
    @ApiModelProperty(value = "类型状态；0：开启，1：关闭，默认：0")
    @Schema(description = "类型状态；0：开启，1：关闭，默认：0")
    private Integer typeStatus;

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
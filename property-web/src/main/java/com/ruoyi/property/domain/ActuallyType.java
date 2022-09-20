package com.ruoyi.property.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实收类型表
 *
 * @author PG
 */
@ApiModel(value = "实收类型表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "actually_type")
public class ActuallyType extends BaseEntity {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 实收类型名称
     */
    @TableField(value = "type_name")
    @ApiModelProperty(value = "实收类型名称")
    @Schema(description = "实收类型名称")
    private String typeName;

    /**
     * 状态,0 未删除 1已删除
     */
    @TableField(value = "is_flag")
    @ApiModelProperty(value = "状态,0 未删除 1已删除")
    @Schema(description = "状态,0 未删除 1已删除")
    private Integer isFlag;

    public static final String COL_ID = "id";

    public static final String COL_TYPE_NAME = "type_name";

    public static final String COL_IS_FLAG = "is_flag";
}
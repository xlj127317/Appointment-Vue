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
 * 巡航管理
 */
@ApiModel(value = "巡航管理")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cruise extends BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @Schema(description = "主键")
    private String id;

    /**
     * 巡航人ID
     */
    @ApiModelProperty(value = "巡航人ID")
    @Schema(description = "巡航人ID")
    private String cruiseId;

    /**
     * 巡航类别
     */
    @ApiModelProperty(value = "巡航类别")
    @Schema(description = "巡航类别")
    private String typeId;

    /**
     * 巡航内容
     */
    @ApiModelProperty(value = "巡航内容")
    @Schema(description = "巡航内容")
    private String cruiseContent;

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
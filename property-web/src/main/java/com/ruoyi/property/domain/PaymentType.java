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
 * 款项类型表
 */
@ApiModel(value = "款项类型表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType extends BaseEntity {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @Schema(description = "主键id")
    private String id;

    /**
     * 款项类别
     */
    @ApiModelProperty(value = "款项类别")
    @Schema(description = "款项类别")
    private String paymentName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Schema(description = "创建时间")
    private Date createTime;
}
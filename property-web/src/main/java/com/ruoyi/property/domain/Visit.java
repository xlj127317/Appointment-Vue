package com.ruoyi.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 来访出入申请表
    */
@ApiModel(value="来访出入申请表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visit {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    @Schema(description="主键")
    private String id;

    /**
    * 来访人
    */
    @ApiModelProperty(value="来访人")
    @Schema(description="来访人")
    private String visitName;

    /**
    * 来访人电话
    */
    @ApiModelProperty(value="来访人电话")
    @Schema(description="来访人电话")
    private String visitPhone;

    /**
    * 来访日期
    */
    @ApiModelProperty(value="来访日期")
    @Schema(description="来访日期")
    private Date visitDate;

    /**
    * 来访理由
    */
    @ApiModelProperty(value="来访理由")
    @Schema(description="来访理由")
    private String reason;

    /**
    * 图片路径
    */
    @ApiModelProperty(value="图片路径")
    @Schema(description="图片路径")
    private String imgSrc;

    /**
    * 申请时间
    */
    @ApiModelProperty(value="申请时间")
    @Schema(description="申请时间")
    private Date applicantTime;

    /**
    * 审核状态，默认0，0为未审核，1为已审核
    */
    @ApiModelProperty(value="审核状态，默认0，0为未审核，1为已审核")
    @Schema(description="审核状态，默认0，0为未审核，1为已审核")
    private Integer auditStatus;

    /**
    * 审核人id
    */
    @ApiModelProperty(value="审核人id")
    @Schema(description="审核人id")
    private String auditId;

    /**
    * 创建人id
    */
    @ApiModelProperty(value="创建人id")
    @Schema(description="创建人id")
    private String createId;

    /**
    * 状态,0 未删除 1已删除
    */
    @ApiModelProperty(value="状态,0 未删除 1已删除")
    @Schema(description="状态,0 未删除 1已删除")
    private Integer isFlag;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    @Schema(description="创建时间")
    private Date createTime;
}
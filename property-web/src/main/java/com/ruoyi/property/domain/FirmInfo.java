package com.ruoyi.property.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 企业信息表
    */
@ApiModel(value="企业信息表")
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirmInfo {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    @Schema(description="主键")
    private String id;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    @Schema(description="用户id")
    private String userId;

    /**
    * 企业名称
    */
    @ApiModelProperty(value="企业名称")
    @Schema(description="企业名称")
    private String firmName;

    /**
    * 法人
    */
    @ApiModelProperty(value="法人")
    @Schema(description="法人")
    private String legalPerson;

    /**
    * 联系人
    */
    @ApiModelProperty(value="联系人")
    @Schema(description="联系人")
    private String contact;

    /**
    * 证件信息 
    */
    @ApiModelProperty(value="证件信息 ")
    @Schema(description="证件信息 ")
    private String idCard;

    /**
    * 行业类别
    */
    @ApiModelProperty(value="行业类别")
    @Schema(description="行业类别")
    private String tradeType;

    /**
    * 建筑面积
    */
    @ApiModelProperty(value="建筑面积")
    @Schema(description="建筑面积")
    private String buildArea;

    /**
    * 占地面积
    */
    @ApiModelProperty(value="占地面积")
    @Schema(description="占地面积")
    private String floorSpace;

    /**
    * 项目投资额
    */
    @ApiModelProperty(value="项目投资额")
    @Schema(description="项目投资额")
    private String investAmount;

    /**
    * 工商信息（含行政许可）可上传附件
    */
    @ApiModelProperty(value="工商信息（含行政许可）可上传附件")
    @Schema(description="工商信息（含行政许可）可上传附件")
    private String businessInfo;

    /**
    * 入园情况 可上传附件
    */
    @ApiModelProperty(value="入园情况 可上传附件")
    @Schema(description="入园情况 可上传附件")
    private String parkInInfo;

    /**
    * 装修情况 可上传附件
    */
    @ApiModelProperty(value="装修情况 可上传附件")
    @Schema(description="装修情况 可上传附件")
    private String furnishInfo;

    /**
    * 企业投资项目备案证情况可上传附件
    */
    @ApiModelProperty(value="企业投资项目备案证情况可上传附件")
    @Schema(description="企业投资项目备案证情况可上传附件")
    private String firmFiling;

    /**
    * 生产工艺 可上传附件
    */
    @ApiModelProperty(value="生产工艺 可上传附件")
    @Schema(description="生产工艺 可上传附件")
    private String prodCraft;

    /**
    * 主要产生污染环节
    */
    @ApiModelProperty(value="主要产生污染环节")
    @Schema(description="主要产生污染环节")
    private String pollLink;

    /**
    * 主要污染物排放量 可上传附件
    */
    @ApiModelProperty(value="主要污染物排放量 可上传附件")
    @Schema(description="主要污染物排放量 可上传附件")
    private String pollAmount;

    /**
    * 水电用量情况
    */
    @ApiModelProperty(value="水电用量情况")
    @Schema(description="水电用量情况")
    private String hydropower;

    /**
    * 环评类别
    */
    @ApiModelProperty(value="环评类别")
    @Schema(description="环评类别")
    private String eiaType;

    /**
    * 环保手续办理情况 可上传附件
    */
    @ApiModelProperty(value="环保手续办理情况 可上传附件")
    @Schema(description="环保手续办理情况 可上传附件")
    private String eiaInfo;

    /**
    * 是否为高新企业 0 不是 1 是
    */
    @ApiModelProperty(value="是否为高新企业 0 不是 1 是")
    @Schema(description="是否为高新企业 0 不是 1 是")
    private Integer isHigh;

    /**
    * 各类荣誉证书
    */
    @ApiModelProperty(value="各类荣誉证书")
    @Schema(description="各类荣誉证书")
    private String honorProof;

    /**
    * 备注
    */
    @ApiModelProperty(value="备注")
    @Schema(description="备注")
    private String remark;

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
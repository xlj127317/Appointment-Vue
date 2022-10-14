package com.ruoyi.property.vo.req;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 工单审核vo
 *
 * @author 心风
 * @date 2022/10/13 17:40
 **/
@Data
public class ReportAuditReq {

    /**
     * 工单id
     */
    @ApiModelProperty(value = "工单id")
    private String id;

    /**
     * 工单类型
     */
    @ApiModelProperty(value = "工单类型")
    private String typeId;

    /**
     * 审批状态
     */

    @ApiModelProperty(value = "审批状态")
    private Integer auditStatus;


    /**
     * 审批内容
     */
    @ApiModelProperty(value = "审批内容")
    private String auditContent;

    /**
     * 审批人id
     */
    @ApiModelProperty(value = "审批人id")
    private String auditId;

    /**
     * 审批时间
     */
    @ApiModelProperty(value = "审批时间")
    private Date auditTime;

}

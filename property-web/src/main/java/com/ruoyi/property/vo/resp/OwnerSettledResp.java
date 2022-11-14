package com.ruoyi.property.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 心风
 * @date 2022/11/10 10:43
 **/
@Data
public class OwnerSettledResp {

    @ApiModelProperty("年份")
    private Integer year;

    /**
     * 月份数据
     */
    @ApiModelProperty("年度月份")
    private Integer month;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    private Integer count;

}

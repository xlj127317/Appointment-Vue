package com.ruoyi.common.core.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 心风
 * @date 2022/09/13 09:39
 **/
@Data
public class PageEntity {

    @ApiModelProperty("limit 分页需要传输的数据")
    public Long limit;

    @ApiModelProperty("page 分页需要传输的数据")
    public Long page;
}

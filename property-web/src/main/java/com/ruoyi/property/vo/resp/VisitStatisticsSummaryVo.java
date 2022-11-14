package com.ruoyi.property.vo.resp;

import lombok.Data;

@Data
public class VisitStatisticsSummaryVo {
    private Long audited;
    private Long unaudited;
    private Long total;
}

package com.ruoyi.property.events;

import com.ruoyi.property.dto.FeeTradeOutputDto;
import lombok.Data;

@Data
public class FeeTradeCompletedEvent {
    private String id;
    private String bizChannel;
    private String outScope;
    private String outId;
}

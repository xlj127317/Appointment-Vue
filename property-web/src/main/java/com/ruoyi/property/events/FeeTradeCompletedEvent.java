package com.ruoyi.property.events;

import com.ruoyi.property.dto.FeeTradeOutputDto;
import lombok.Data;

@Data
public class FeeTradeCompletedEvent {
    private FeeTradeOutputDto feeTrade;
}

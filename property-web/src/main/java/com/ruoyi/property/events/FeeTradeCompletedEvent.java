package com.ruoyi.property.events;

import com.ruoyi.property.dto.FeeTradeOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEvent;

@Data
public class FeeTradeCompletedEvent extends ApplicationEvent {
    private FeeTradeOutputDto feeTrade;

    public FeeTradeCompletedEvent(Object source) {
        super(source);
    }
}

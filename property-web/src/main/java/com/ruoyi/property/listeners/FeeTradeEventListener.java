package com.ruoyi.property.listeners;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.property.domain.Receivable;
import com.ruoyi.property.events.FeeTradeCompletedEvent;
import com.ruoyi.property.service.IReceivableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FeeTradeEventListener {
    @Autowired
    private IReceivableService receivableService;

    @EventListener
    public void onCompleteEvent(FeeTradeCompletedEvent event) throws Exception {
        switch (event.getOutScope()) {
            case "receivable":
                handleReceivableComplete(event);
                break;
        }
    }

    private void handleReceivableComplete(FeeTradeCompletedEvent event) {
        String receivableId = event.getOutId();

        Receivable receivable = receivableService.selectReceivableById(receivableId);
        receivable.setActuallyStatus(1);
        receivable.setActuallyMoney(event.getPaidAmount().toString());
        receivable.setActuallyDate(event.getPaidAt());
        receivable.setPaymentType(0);
        receivableService.updateReceivable(receivable);
    }
}

package com.ruoyi.web.controller.web;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.property.dto.FeeTradeListInputDto;
import com.ruoyi.property.service.IFeeTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hcx/property/feetrades")
public class FeeTradesController extends BaseController {
    @Autowired
    private IFeeTradeService feeTradeService;

    @PreAuthorize("@ss.hasPermi('property:feetrades:list')")
    @PostMapping("")
    public TableDataInfo list(@RequestBody FeeTradeListInputDto input)
    {
        startPage();
        return getDataTable(feeTradeService.listAllTrades(input));
    }
}

package com.ruoyi.web.controller.web;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.property.dto.FeeTradeListInputDto;
import com.ruoyi.property.dto.FeeTradeOutputDto;
import com.ruoyi.property.service.IFeeTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Map> trades = feeTradeService.listTrades(input);
        List<FeeTradeOutputDto> output = trades.stream()
                .map(x -> BeanUtil.mapToBean(x, FeeTradeOutputDto.class, true, CopyOptions.create()))
                .collect(Collectors.toList());
        return getDataTable(output);
    }

    @GetMapping("/{tradeId}")
    public com.ruoyi.property.dto.wx.FeeTradeOutputDto getFeeTrade(@PathVariable String tradeId)
    {
        Map params = new HashMap();
        params.put("id", tradeId);
        Map trade = feeTradeService.getTrade(params);
        FeeTradeOutputDto dto = BeanUtil.mapToBean(trade, FeeTradeOutputDto.class, false, new CopyOptions());
        return dto;
    }
}

package com.ruoyi.property.service;

import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.FeeTradeListInputDto;

import java.util.List;
import java.util.Map;

public interface IFeeTradeService {
    void createTrade(Map params);
    List<Map> listUserTrades(String ownerId);
    List<Map> listAllTrades(FeeTradeListInputDto input);
    Map getTradeByNo(String no);
    Map getTrade(Map params);
    int completeTrade(Map params);
}

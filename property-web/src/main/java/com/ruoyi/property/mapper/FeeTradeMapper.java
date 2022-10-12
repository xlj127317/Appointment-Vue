package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.FeeTrade;

import java.util.List;
import java.util.Map;

public interface FeeTradeMapper {
    void create(Map params);
    List<Map> listUserTrades(String ownerId);

    List<Map> listAllTrades(Map params);

    Map getTradeByNo(String tradeNo);

    Map getTrade(Map params);

    int completeTrade(Map params);
}

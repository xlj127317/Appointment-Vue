package com.ruoyi.property.mapper;

import java.util.List;
import java.util.Map;

public interface FeeTradeMapper {
    void create(Map params);
    List<Map> listUserTrades(String ownerId);

    List<Map> listTrades(Map params);

    Map getTradeByNo(String tradeNo);

    Map getTrade(Map params);

    int completeTrade(Map params);
}

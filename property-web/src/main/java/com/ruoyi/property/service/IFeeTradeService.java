package com.ruoyi.property.service;

import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.AmountValueChartDto;

import java.util.List;
import java.util.Map;

public interface IFeeTradeService {
    FeeTrade createTrade(Map params);
    void deleteTrade(String id);
    List<Map> listUserTrades(String ownerId);
    List<Map> listTrades(Map params);
    Map getTrade(Map params);
    Map getTradeById(String id, SqlLockMode lockMode);
    Map getTradeByNo(String no);
    Map getTradeByNo(String no, SqlLockMode lockMode);
    Map getOwnedTradeByNo(String ownerId, String no, SqlLockMode lockMode);
    int completeTrade(Map params);

    AmountValueChartDto getAmountValueChart(int year);
}

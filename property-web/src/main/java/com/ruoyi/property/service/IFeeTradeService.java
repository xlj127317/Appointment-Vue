package com.ruoyi.property.service;

import com.ruoyi.common.enums.DbLockStrength;
import com.ruoyi.property.dto.FeeTradeListInputDto;

import java.util.List;
import java.util.Map;

public interface IFeeTradeService {
    void createTrade(Map params);
    List<Map> listUserTrades(String ownerId);
    List<Map> listTrades(Map params);
    Map getTrade(Map params);
    Map getTradeById(String id, DbLockStrength withLock);
    Map getTradeByNo(String no);
    Map getTradeByNo(String no, DbLockStrength withLock);
    Map getOwnedTradeByNo(String ownerId, String no, DbLockStrength withLock);
    int completeTrade(Map params);
    List<Map> statisticSummaryByMonth(int year);
    List<Map> statisticSummaryByQuarter(int year);
}

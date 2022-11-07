package com.ruoyi.property.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.AggregatedSumDto;
import com.ruoyi.property.dto.AmountValueChartDto;
import com.ruoyi.property.mapper.FeeTradeMapper;
import com.ruoyi.property.service.IFeeTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeeTradeServiceImpl implements IFeeTradeService {
    @Autowired
    private FeeTradeMapper feeTradeMapper;

    @Override
    public FeeTrade createTrade(Map params) {
        String id = PkeyGenerator.getUniqueString();
        params.put("id", id);
        params.put("no", PkeyGenerator.getUniqueString());
        BigDecimal price = (BigDecimal) params.get("price");
        BigDecimal count = (BigDecimal) params.get("count");
        BigDecimal amount = price.multiply(count);
        params.put("amount", amount);
        params.put("state", FeeTradeState.WAIT_PAY.getValue());
        feeTradeMapper.create(params);

        params.clear();
        params.put("id", id);
        Map feeTradeMap = feeTradeMapper.getTrade(params);
        return BeanUtil.mapToBean(feeTradeMap, FeeTrade.class, true, CopyOptions.create());
    }

    @Override
    public void deleteTrade(String id) {
        feeTradeMapper.deleteTrade(id);
    }

    @Override
    public List<Map> listUserTrades(String ownerId) {
        return feeTradeMapper.listUserTrades(ownerId);
    }

    @Override
    public List<Map> listTrades(Map params) {
        return feeTradeMapper.listTrades(params);
    }

    @Override
    public Map getTradeByNo(String no) {
        return feeTradeMapper.getTradeByNo(no);
    }

    @Override
    public Map getTrade(Map params) {
        return feeTradeMapper.getTrade(params);
    }

    @Override
    public Map getTradeById(String id, SqlLockMode lockMode) {
        Map params = new HashMap();
        params.put("id", id);
        params.put("lockMode", lockMode);
        return getTrade(params);
    }

    @Override
    public Map getTradeByNo(String no, SqlLockMode lockMode) {
        Map params = new HashMap();
        params.put("no", no);
        params.put("lockMode", lockMode);
        return getTrade(params);
    }

    @Override
    public Map getOwnedTradeByNo(String ownerId, String no, SqlLockMode lockMode) {
        Map params = new HashMap();
        params.put("ownerId", ownerId);
        params.put("no", no);
        params.put("lockMode", lockMode);
        return getTrade(params);
    }

    @Override
    public int completeTrade(Map params) {
        params = new HashMap(params);
        params.put("stateSucceedValue", FeeTradeState.SUCCEED.getValue());
        return feeTradeMapper.completeTrade(params);
    }

    @Override
    public AmountValueChartDto getAmountValueChart(int year) {
        List<AggregatedSumDto> byMonth = feeTradeMapper.getAmountGroupByMonth(year);
        List<AggregatedSumDto> byQuarter = feeTradeMapper.getAmountGroupByQuarter(year);
        return new AmountValueChartDto.Builder()
                .addMonths(byMonth)
                .addQuarters(byQuarter)
                .build();
    }
}

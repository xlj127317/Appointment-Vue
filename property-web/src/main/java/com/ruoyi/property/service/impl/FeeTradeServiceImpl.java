package com.ruoyi.property.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.dto.FeeTradeListInputDto;
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
    public void createTrade(Map params) {
        params.put("id", PkeyGenerator.getUniqueString());
        params.put("no", PkeyGenerator.getUniqueString());
        BigDecimal price = (BigDecimal) params.get("price");
        BigDecimal count = (BigDecimal) params.get("count");
        BigDecimal amount = price.multiply(count);
        params.put("amount", amount);
        params.put("state", FeeTradeState.WAIT_PAY);
        feeTradeMapper.create(params);
    }

    @Override
    public List<Map> listUserTrades(String ownerId) {
        return feeTradeMapper.listUserTrades(ownerId);
    }

    @Override
    public List<Map> listTrades(FeeTradeListInputDto input) {
        Map params = BeanUtil.beanToMap(input);
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
    public int completeTrade(Map params) {
        params = new HashMap(params);
        params.put("stateSucceedValue", FeeTradeState.SUCCEED.getValue());
        return feeTradeMapper.completeTrade(params);
    }
}

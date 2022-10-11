package com.ruoyi.property.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.FeeTradeListInputDto;
import com.ruoyi.property.mapper.FeeTradeMapper;
import com.ruoyi.property.service.IFeeTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        BigDecimal price = (BigDecimal)params.get("price");
        BigDecimal count = (BigDecimal)params.get("count");
        BigDecimal amount = price.multiply(count);
        params.put("amount", amount);
        params.put("state", FeeTradeState.WAIT_PAY);
        feeTradeMapper.create(params);
    }

    @Override
    public List<Map> listUserTrades(String ownerId)
    {
        return feeTradeMapper.listUserTrades(ownerId);
    }

    @Override
    public List<Map> listAllTrades(FeeTradeListInputDto input)
    {
        Map params = BeanUtil.beanToMap(input);
        return feeTradeMapper.listAllTrades(params);
    }

    @Override
    public Map getTradeByNo(String no)
    {
        return feeTradeMapper.getTradeByNo(no);
    }

    @Override
    public FeeTrade getTrade(Map params)
    {
        return getTrade(params, 0);
    }

    @Override
    public FeeTrade getTrade(Map params, int lockType)
    {
        return feeTradeMapper.getTrade(params, lockType);
    }
}

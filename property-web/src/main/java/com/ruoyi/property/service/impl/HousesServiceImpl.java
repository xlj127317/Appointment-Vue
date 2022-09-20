package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.property.domain.Houses;
import com.ruoyi.property.mapper.HousesMapper;
import com.ruoyi.property.service.HousesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/15 14:32
 **/
@Service
public class HousesServiceImpl implements HousesService {

    @Autowired
    private HousesMapper housesMapper;

    @Override
    public AjaxResult insertHouses(Houses houses) {
        houses.setId(IdUtils.fastSimpleUUID());
        houses.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        houses.setIsFlag(0);
        return AjaxResult.success(housesMapper.insert(houses));
    }

    @Override
    public AjaxResult queryById(String id) {
        return AjaxResult.success(housesMapper.selectById(id));
    }

    @Override
    public AjaxResult updateHousesById(Houses houses) {
        return AjaxResult.success(housesMapper.updateByPrimaryKeySelective(houses));
    }

    @Override
    public int deleteById(String[] ids) {
        return housesMapper.updateByIds(ids);
    }

    @Override
    public List<Houses> queryList(Houses houses) {
        return housesMapper.queryHousesList(houses);
    }
}

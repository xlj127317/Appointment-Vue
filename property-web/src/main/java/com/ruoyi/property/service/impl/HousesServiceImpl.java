package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.property.domain.Houses;
import com.ruoyi.property.mapper.HousesMapper;
import com.ruoyi.property.service.HousesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/15 14:32
 **/
@Service
public class HousesServiceImpl extends ServiceImpl<HousesMapper, Houses> implements HousesService {

    @Resource
    private HousesMapper housesMapper;

    @Override
    public AjaxResult insertHouses(Houses houses) {
        houses.setId(IdUtils.fastSimpleUUID());
        houses.setHousesStatus(0);
        houses.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        houses.setIsFlag(0);
        return AjaxResult.success(housesMapper.insert(houses));
    }

    @Override
    public AjaxResult queryById(String id) {
        LambdaQueryWrapper<Houses> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Houses::getId, id);
        queryWrapper.eq(Houses::getIsFlag, 0);
        return AjaxResult.success(housesMapper.selectOne(queryWrapper));
    }

    @Override
    public AjaxResult deleteById(String[] ids) {
        return AjaxResult.success(housesMapper.updateByIds(ids));
    }

    @Override
    public List<Houses> queryList(Houses houses) {
        return housesMapper.queryHousesList(houses);
    }

    @Override
    public AjaxResult updateStatus(String id) {
        LambdaUpdateWrapper<Houses> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Houses::getId, id);
        updateWrapper.set(Houses::getHousesStatus, 1);
        return AjaxResult.success(housesMapper.update(new Houses(), updateWrapper));
    }
}

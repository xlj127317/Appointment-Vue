package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.property.domain.Actually;
import com.ruoyi.property.domain.ActuallyType;
import com.ruoyi.property.mapper.ActuallyTypeMapper;
import com.ruoyi.property.service.ActuallyTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 17:33
 **/
@Service
public class ActuallyTypeServiceImpl extends ServiceImpl<ActuallyTypeMapper, ActuallyType> implements ActuallyTypeService {

    @Resource
    private ActuallyTypeMapper actuallyTypeMapper;

    @Override
    public AjaxResult insertActuallyType(ActuallyType actuallyType) {
        actuallyType.setId(IdUtils.fastSimpleUUID());
        actuallyType.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        return AjaxResult.success(actuallyTypeMapper.insertSelective(actuallyType));
    }

    @Override
    public AjaxResult queryById(String id) {
        LambdaQueryWrapper<ActuallyType> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ActuallyType::getId,id);
        queryWrapper.eq(ActuallyType::getIsFlag,0);
        return AjaxResult.success(actuallyTypeMapper.selectOne(queryWrapper));
    }

    @Override
    public AjaxResult deleteById(String[] ids) {
        return AjaxResult.success(actuallyTypeMapper.updateByIds(ids));
    }

    @Override
    public List<ActuallyType> queryList(ActuallyType actuallyType) {
        return actuallyTypeMapper.selectActuallyList(actuallyType);
    }
}

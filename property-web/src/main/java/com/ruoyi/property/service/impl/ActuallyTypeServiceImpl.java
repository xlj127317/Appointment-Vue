package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
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
public class ActuallyTypeServiceImpl implements ActuallyTypeService {

    @Resource
    private ActuallyTypeMapper actuallyTypeMapper;

    @Override
    public AjaxResult insertActuallyType(ActuallyType actuallyType) {
        actuallyType.setId(PkeyGenerator.getUniqueString());
        actuallyType.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        return AjaxResult.success(actuallyTypeMapper.insertSelective(actuallyType));
    }

    @Override
    public AjaxResult queryById(String id) {
        return AjaxResult.success(actuallyTypeMapper.selectOne(id));
    }

    @Override
    public int deleteById(String[] ids) {
        return actuallyTypeMapper.updateByIds(ids);
    }

    @Override
    public List<ActuallyType> queryList(ActuallyType actuallyType) {
        return actuallyTypeMapper.selectActuallyList(actuallyType);
    }

    @Override
    public AjaxResult updateById(ActuallyType actuallyType) {

        return AjaxResult.success(actuallyTypeMapper.updateByPrimaryKeySelective(actuallyType));
    }
}

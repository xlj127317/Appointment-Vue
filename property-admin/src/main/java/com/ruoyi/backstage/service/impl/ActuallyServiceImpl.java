package com.ruoyi.backstage.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.backstage.domain.Actually;
import com.ruoyi.backstage.mapper.ActuallyMapper;
import com.ruoyi.backstage.service.ActuallyService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 心风
 * @date 2022/09/09 15:10
 **/
@Service
public class ActuallyServiceImpl extends ServiceImpl<ActuallyMapper, Actually> implements ActuallyService {

    @Resource
    private ActuallyMapper actuallyMapper;

    @Override
    public AjaxResult insertActually(@Validated Actually actually) {
        actually.setId(IdUtils.fastSimpleUUID());
        actually.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        return AjaxResult.success(actuallyMapper.insertSelective(actually));
    }

    @Override
    public AjaxResult queryById(String id) {
        LambdaQueryWrapper<Actually> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Actually::getId,id);
        queryWrapper.eq(Actually::getIsFlag,0);
        return AjaxResult.success(actuallyMapper.selectOne(queryWrapper));
    }

    @Override
    public AjaxResult deleteById(String[] ids) {
        return AjaxResult.success(actuallyMapper.updateByIds(ids));
    }

    @Override
    public List<Actually> queryList(Actually actually) {
        return actuallyMapper.selectActuallyList(actually);
    }
}

package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.property.domain.Assets;
import com.ruoyi.property.mapper.AssetsMapper;
import com.ruoyi.property.service.AssetsService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 14:13
 **/
@Service
public class AssetsServiceImpl extends ServiceImpl<AssetsMapper, Assets> implements AssetsService {

    @Resource
    private AssetsMapper assetsMapper;

    @Override
    public AjaxResult insertAssets(Assets assets) {
        assets.setId(IdUtils.fastSimpleUUID());
        assets.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        return AjaxResult.success(assetsMapper.insertSelective(assets));
    }

    @Override
    public AjaxResult queryById(String id) {
        LambdaQueryWrapper<Assets> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Assets::getId, id);
        queryWrapper.eq(Assets::getIsFlag, 0);
        return AjaxResult.success(assetsMapper.selectOne(queryWrapper));
    }

    @Override
    public AjaxResult deleteById(String[] ids) {
        return AjaxResult.success(assetsMapper.updateByIds(ids));
    }

    @Override
    public List<Assets> queryList(Assets assets) {
        return assetsMapper.queryHousesList(assets);
    }
}

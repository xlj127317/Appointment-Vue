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

    /**
     * 实收开始时间
     */
    public static final String ACTUALLY_START_TIME = "actuallyStartTime";

    /**
     * 实收结束时间
     */
    public static final String ACTUALLY_STOP_TIME = "actuallyStopTime";

    /**
     * 创建开始时间
     */
    public static final String CREATE_START_TIME = "createStartTime";

    /**
     * 创建结束时间
     */
    public static final String CREATE_STOP_TIME = "createStopTime";


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
        return AjaxResult.success(actuallyMapper.selectByPrimaryKey(id));
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

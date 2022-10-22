package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Actually;
import com.ruoyi.property.mapper.ActuallyMapper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.property.service.ActuallyService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/09 15:10
 **/
@Service
public class ActuallyServiceImpl implements ActuallyService {

    @Resource
    private ActuallyMapper actuallyMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public AjaxResult insertActually(@Validated Actually actually) {
        actually.setId(PkeyGenerator.getUniqueString());
        actually.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        return AjaxResult.success(actuallyMapper.insertSelective(actually));
    }

    @Override
    public AjaxResult queryById(String id) {
        return AjaxResult.success(actuallyMapper.selectOne(id));
    }

    @Override
    public int deleteById(String[] ids) {
        return actuallyMapper.updateByIds(ids);
    }

    @Override
    public List<Actually> queryList(Actually actually) {
        List<Actually> actuallies = actuallyMapper.selectActuallyList(actually);
        for (Actually act : actuallies) {
            SysUser sysUser = sysUserMapper.selectUserById(act.getCreateId());
            if (ObjectUtil.isNull(sysUser)) {
                throw new ServiceException("无该对象：" + act.getCreateId());
            }
            act.setCreateId(sysUser.getNickName());
        }
        return actuallies;
    }

    @Override
    public AjaxResult updateById(Actually actually) {
        return AjaxResult.success(actuallyMapper.updateByPrimaryKeySelective(actually));
    }
}

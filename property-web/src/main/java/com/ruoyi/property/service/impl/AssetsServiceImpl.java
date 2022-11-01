package com.ruoyi.property.service.impl;

import cn.hutool.core.date.CalendarUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Assets;
import com.ruoyi.property.mapper.AssetsMapper;
import com.ruoyi.property.service.AssetsService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 14:13
 **/
@Service
public class AssetsServiceImpl implements AssetsService {

    @Resource
    private AssetsMapper assetsMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public AjaxResult insertAssets(Assets assets) {
        assets.setId(PkeyGenerator.getUniqueString());
        assets.setCreateTime(DateUtil.date(CalendarUtil.calendar()));
        return AjaxResult.success(assetsMapper.insertSelective(assets));
    }

    @Override
    public AjaxResult queryById(String id) {
        Assets assets = assetsMapper.selectOne(id);
        return AjaxResult.success(assets);
    }

    @Override
    public int deleteById(String[] ids) {
        return assetsMapper.updateByIds(ids);
    }

    @Override
    public List<Assets> queryList(Assets assets) {
        List<Assets> list = assetsMapper.queryHousesList(assets);
        for (Assets ass : list) {
            String nickName = sysUserMapper.nickNameById(ass.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + ass.getCreateId(), 201);
            }
            ass.setCreateId(nickName);
        }
        return list;
    }

    @Override
    public AjaxResult updateById(Assets assets) {
        return AjaxResult.success(assetsMapper.updateByPrimaryKeySelective(assets));
    }
}

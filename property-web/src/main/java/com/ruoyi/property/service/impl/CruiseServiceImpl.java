package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.service.CruiseService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.CruiseMapper;
import com.ruoyi.property.domain.Cruise;

import javax.annotation.Resource;

/**
 * 巡航管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-24
 */
@Service
public class CruiseServiceImpl implements CruiseService {
    @Resource
    private CruiseMapper cruiseMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询巡航管理
     *
     * @param id 巡航管理主键
     * @return 巡航管理
     */
    @Override
    public Cruise selectCruiseById(String id) {
        return cruiseMapper.selectCruiseById(id);
    }

    /**
     * 查询巡航管理列表
     *
     * @param cruise 巡航管理
     * @return 巡航管理
     */
    @Override
    public List<Cruise> selectCruiseList(Cruise cruise) {
        List<Cruise> list = cruiseMapper.selectCruiseList(cruise);
        for (Cruise cru : list) {
            SysUser sysUser = sysUserMapper.selectUserById(cru.getCreateId());
            if (ObjectUtil.isNull(sysUser)) {
                throw new ServiceException("无该对象：" + cru.getCreateId());
            }
            cru.setCreateId(sysUser.getNickName());
        }
        return list;
    }

    /**
     * 新增巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    @Override
    public int insertCruise(Cruise cruise) {
        cruise.setId(PkeyGenerator.getUniqueString());
        cruise.setCreateTime(DateUtils.getNowDate());
        return cruiseMapper.insertCruise(cruise);
    }

    /**
     * 修改巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    @Override
    public int updateCruise(Cruise cruise) {
        return cruiseMapper.updateCruise(cruise);
    }

    /**
     * 批量删除巡航管理
     *
     * @param ids 需要删除的巡航管理主键
     * @return 结果
     */
    @Override
    public int deleteCruiseByIds(String[] ids) {
        return cruiseMapper.deleteCruiseByIds(ids);
    }

    /**
     * 删除巡航管理信息
     *
     * @param id 巡航管理主键
     * @return 结果
     */
    @Override
    public int deleteCruiseById(String id) {
        return cruiseMapper.deleteCruiseById(id);
    }
}

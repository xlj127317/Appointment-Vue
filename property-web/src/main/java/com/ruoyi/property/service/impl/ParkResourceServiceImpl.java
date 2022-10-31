package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ParkResourceMapper;
import com.ruoyi.property.domain.ParkResource;
import com.ruoyi.property.service.ParkResourceService;

import javax.annotation.Resource;

/**
 * 园区资源Service业务层处理
 *
 * @author wind
 * @date 2022-10-18
 */
@Service
public class ParkResourceServiceImpl implements ParkResourceService {
    @Resource
    private ParkResourceMapper parkResourceMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询园区资源
     *
     * @param id 园区资源主键
     * @return 园区资源
     */
    @Override
    public ParkResource selectParkResourceById(String id) {
        ParkResource parkResource = parkResourceMapper.selectParkResourceById(id);
        String nickName = sysUserMapper.nickNameById(parkResource.getCreateId());
        if (StrUtil.isBlank(nickName)) {
            throw new ServiceException("无此创建人：" + parkResource.getCreateId(), 201);
        }
        parkResource.setCreateId(nickName);
        return parkResource;
    }

    /**
     * 查询园区资源列表
     *
     * @param parkResource 园区资源
     * @return 园区资源
     */
    @Override
    public List<ParkResource> selectParkResourceList(ParkResource parkResource) {
        List<ParkResource> list = parkResourceMapper.selectParkResourceList(parkResource);
        for (ParkResource resource : list) {
            String nickName = sysUserMapper.nickNameById(resource.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + resource.getCreateId(), 201);
            }
            resource.setCreateId(nickName);
        }
        return list;
    }

    /**
     * 新增园区资源
     *
     * @param parkResource 园区资源
     * @return 结果
     */
    @Override
    public int insertParkResource(ParkResource parkResource) {
        parkResource.setId(PkeyGenerator.getUniqueString());
        parkResource.setCreateTime(DateUtils.getNowDate());
        return parkResourceMapper.insertParkResource(parkResource);
    }

    /**
     * 修改园区资源
     *
     * @param parkResource 园区资源
     * @return 结果
     */
    @Override
    public int updateParkResource(ParkResource parkResource) {
        return parkResourceMapper.updateParkResource(parkResource);
    }

    /**
     * 批量删除园区资源
     *
     * @param ids 需要删除的园区资源主键
     * @return 结果
     */
    @Override
    public int deleteParkResourceByIds(String[] ids) {
        return parkResourceMapper.deleteParkResourceByIds(ids);
    }

    /**
     * 删除园区资源信息
     *
     * @param id 园区资源主键
     * @return 结果
     */
    @Override
    public int deleteParkResourceById(String id) {
        return parkResourceMapper.deleteParkResourceById(id);
    }
}

package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.OwnerMapper;
import com.ruoyi.property.domain.Owner;
import com.ruoyi.property.service.IOwnerService;

/**
 * 业主管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class OwnerServiceImpl implements IOwnerService {
    @Autowired
    private OwnerMapper ownerMapper;

    /**
     * 查询业主管理
     *
     * @param id 业主管理主键
     * @return 业主管理
     */
    @Override
    public Owner selectOwnerById(String id) {
        return ownerMapper.selectOwnerById(id);
    }

    /**
     * 查询业主管理列表
     *
     * @param owner 业主管理
     * @return 业主管理
     */
    @Override
    public List<Owner> selectOwnerList(Owner owner) {
        return ownerMapper.selectOwnerList(owner);
    }

    /**
     * 新增业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    @Override
    public int insertOwner(Owner owner) {
        owner.setId(PkeyGenerator.getUniqueString());
        owner.setCreateTime(DateUtils.getNowDate());
        return ownerMapper.insertOwner(owner);
    }

    /**
     * 修改业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    @Override
    public int updateOwner(Owner owner) {
        return ownerMapper.updateOwner(owner);
    }

    /**
     * 批量删除业主管理
     *
     * @param ids 需要删除的业主管理主键
     * @return 结果
     */
    @Override
    public int deleteOwnerByIds(String[] ids) {
        return ownerMapper.deleteOwnerByIds(ids);
    }

    /**
     * 删除业主管理信息
     *
     * @param id 业主管理主键
     * @return 结果
     */
    @Override
    public int deleteOwnerById(String id) {
        return ownerMapper.deleteOwnerById(id);
    }
}
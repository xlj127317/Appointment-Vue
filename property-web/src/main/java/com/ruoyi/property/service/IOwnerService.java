package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Owner;

/**
 * 业主管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IOwnerService {
    /**
     * 查询业主管理
     *
     * @param id 业主管理主键
     * @return 业主管理
     */
    Owner selectOwnerById(String id);

    /**
     * 查询业主管理列表
     *
     * @param owner 业主管理
     * @return 业主管理集合
     */
    List<Owner> selectOwnerList(Owner owner);

    /**
     * 新增业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    int insertOwner(Owner owner);

    /**
     * 修改业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    int updateOwner(Owner owner);

    /**
     * 批量删除业主管理
     *
     * @param ids 需要删除的业主管理主键集合
     * @return 结果
     */
    int deleteOwnerByIds(String[] ids);

    /**
     * 删除业主管理信息
     *
     * @param id 业主管理主键
     * @return 结果
     */
    int deleteOwnerById(String id);
}

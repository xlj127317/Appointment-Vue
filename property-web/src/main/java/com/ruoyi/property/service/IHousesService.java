package com.ruoyi.property.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.property.domain.Houses;

/**
 * 房屋管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
 public interface IHousesService {
    /**
     * 查询房屋管理
     *
     * @param id 房屋管理主键
     * @return 房屋管理
     */
     Houses selectHousesById(String id);

    /**
     * 查询房屋管理列表
     *
     * @param houses 房屋管理
     * @return 房屋管理集合
     */
     List<Houses> selectHousesList(Houses houses);

    /**
     * 新增房屋管理
     *
     * @param houses 房屋管理
     * @return 结果
     */
     int insertHouses(Houses houses);

    /**
     * 修改房屋管理
     *
     * @param houses 房屋管理
     * @return 结果
     */
     int updateHouses(Houses houses);

    /**
     * 批量删除房屋管理
     *
     * @param ids 需要删除的房屋管理主键集合
     * @return 结果
     */
     int deleteHousesByIds(String[] ids);

    /**
     * 删除房屋管理信息
     *
     * @param id 房屋管理主键
     * @return 结果
     */
     int deleteHousesById(String id);

    Map<String,String> selectOwnerInfo(String id);
}

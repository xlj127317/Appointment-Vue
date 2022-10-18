package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.ParkResource;

/**
 * 园区资源Service接口
 *
 * @author wind
 * @date 2022-10-18
 */
public interface ParkResourceService {
    /**
     * 查询园区资源
     *
     * @param id 园区资源主键
     * @return 园区资源
     */
    ParkResource selectParkResourceById(String id);

    /**
     * 查询园区资源列表
     *
     * @param parkResource 园区资源
     * @return 园区资源集合
     */
    List<ParkResource> selectParkResourceList(ParkResource parkResource);

    /**
     * 新增园区资源
     *
     * @param parkResource 园区资源
     * @return 结果
     */
    int insertParkResource(ParkResource parkResource);

    /**
     * 修改园区资源
     *
     * @param parkResource 园区资源
     * @return 结果
     */
    int updateParkResource(ParkResource parkResource);

    /**
     * 批量删除园区资源
     *
     * @param ids 需要删除的园区资源主键集合
     * @return 结果
     */
    int deleteParkResourceByIds(String[] ids);

    /**
     * 删除园区资源信息
     *
     * @param id 园区资源主键
     * @return 结果
     */
    int deleteParkResourceById(String id);
}

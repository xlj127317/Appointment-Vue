package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Cruise;

/**
 * 巡航管理Service接口
 *
 * @author wind
 * @date 2022-09-24
 */
public interface CruiseService {
    /**
     * 查询巡航管理
     *
     * @param id 巡航管理主键
     * @return 巡航管理
     */
    public Cruise selectCruiseById(String id);

    /**
     * 查询巡航管理列表
     *
     * @param cruise 巡航管理
     * @return 巡航管理集合
     */
    public List<Cruise> selectCruiseList(Cruise cruise);

    /**
     * 新增巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    public int insertCruise(Cruise cruise);

    /**
     * 修改巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    public int updateCruise(Cruise cruise);

    /**
     * 批量删除巡航管理
     *
     * @param ids 需要删除的巡航管理主键集合
     * @return 结果
     */
    public int deleteCruiseByIds(String[] ids);

    /**
     * 删除巡航管理信息
     *
     * @param id 巡航管理主键
     * @return 结果
     */
    public int deleteCruiseById(String id);
}

package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.RentSell;

/**
 * 租售管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IRentSellService {
    /**
     * 查询租售管理
     *
     * @param id 租售管理主键
     * @return 租售管理
     */
    RentSell selectRentSellById(String id);

    /**
     * 查询租售管理列表
     *
     * @param rentSell 租售管理
     * @return 租售管理集合
     */
    List<RentSell> selectRentSellList(RentSell rentSell);

    /**
     * 新增租售管理
     *
     * @param rentSell 租售管理
     * @return 结果
     */
    int insertRentSell(RentSell rentSell);

    /**
     * 修改租售管理
     *
     * @param rentSell 租售管理
     * @return 结果
     */
    int updateRentSell(RentSell rentSell);

    /**
     * 批量删除租售管理
     *
     * @param ids 需要删除的租售管理主键集合
     * @return 结果
     */
    int deleteRentSellByIds(String[] ids);

    /**
     * 删除租售管理信息
     *
     * @param id 租售管理主键
     * @return 结果
     */
    int deleteRentSellById(String id);
}

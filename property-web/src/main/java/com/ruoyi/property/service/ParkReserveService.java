package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.ParkReserve;

/**
 * 园区资源预约Service接口
 *
 * @author wind
 * @date 2022-10-18
 */
public interface ParkReserveService {
    /**
     * 查询园区资源预约
     *
     * @param id 园区资源预约主键
     * @return 园区资源预约
     */
    ParkReserve selectParkReserveById(String id);

    /**
     * 查询园区资源预约列表
     *
     * @param parkReserve 园区资源预约
     * @return 园区资源预约集合
     */
    List<ParkReserve> selectParkReserveList(ParkReserve parkReserve);

    /**
     * 新增园区资源预约
     *
     * @param parkReserve 园区资源预约
     * @return 结果
     */
    int insertParkReserve(ParkReserve parkReserve);

    /**
     * 修改园区资源预约
     *
     * @param parkReserve 园区资源预约
     * @return 结果
     */
    int updateParkReserve(ParkReserve parkReserve);

    /**
     * 批量删除园区资源预约
     *
     * @param ids 需要删除的园区资源预约主键集合
     * @return 结果
     */
    int deleteParkReserveByIds(String[] ids);

    /**
     * 删除园区资源预约信息
     *
     * @param id 园区资源预约主键
     * @return 结果
     */
    int deleteParkReserveById(String id);
}

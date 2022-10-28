package com.ruoyi.property.mapper;

import java.util.List;

import com.ruoyi.property.domain.ParkReserve;
import org.apache.ibatis.annotations.Param;

/**
 * 园区资源预约Mapper接口
 *
 * @author wind
 * @date 2022-10-18
 */
public interface ParkReserveMapper {
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
     * 删除园区资源预约
     *
     * @param id 园区资源预约主键
     * @return 结果
     */
    int deleteParkReserveById(String id);

    /**
     * 批量删除园区资源预约
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteParkReserveByIds(String[] ids);

    /**
     * 园区资源审核
     *
     * @param id          审核id
     * @param auditStatus 审核状态
     * @return int
     */
    int updateAudit(@Param("id") String id, @Param("auditStatus") Integer auditStatus);
}

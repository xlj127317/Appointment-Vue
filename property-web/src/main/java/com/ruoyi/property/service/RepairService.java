package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Repair;
import com.ruoyi.property.dto.AmountValueChartDto;

/**
 * 物业报修申请Service接口
 *
 * @author wind
 * @date 2022-09-29
 */
public interface RepairService {
    /**
     * 查询物业报修申请
     *
     * @param id 物业报修申请主键
     * @return 物业报修申请
     */
     Repair selectRepairById(String id);

    /**
     * 查询物业报修申请列表
     *
     * @param repair 物业报修申请
     * @return 物业报修申请集合
     */
     List<Repair> selectRepairList(Repair repair);

    /**
     * 新增物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
     int insertRepair(Repair repair);

    /**
     * 修改物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
     int updateRepair(Repair repair);

    /**
     * 批量删除物业报修申请
     *
     * @param ids 需要删除的物业报修申请主键集合
     * @return 结果
     */
     int deleteRepairByIds(String[] ids);

    /**
     * 删除物业报修申请信息
     *
     * @param id 物业报修申请主键
     * @return 结果
     */
     int deleteRepairById(String id);

    AmountValueChartDto getAmountValueChart(int year);
}

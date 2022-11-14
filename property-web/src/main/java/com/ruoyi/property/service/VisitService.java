package com.ruoyi.property.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.property.domain.Visit;
import com.ruoyi.property.dto.AmountValueChartDto;

/**
 * 来访出入申请Service接口
 *
 * @author wind
 * @date 2022-09-29
 */
public interface VisitService {
    /**
     * 查询来访出入申请
     *
     * @param id 来访出入申请主键
     * @return 来访出入申请
     */
     Visit selectVisitById(String id);

    /**
     * 查询来访出入申请列表
     *
     * @param visit 来访出入申请
     * @return 来访出入申请集合
     */
     List<Visit> selectVisitList(Visit visit);

    /**
     * 新增来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
     int insertVisit(Visit visit);

    /**
     * 修改来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
     int updateVisit(Visit visit);

    /**
     * 批量删除来访出入申请
     *
     * @param ids 需要删除的来访出入申请主键集合
     * @return 结果
     */
     int deleteVisitByIds(String[] ids);

    /**
     * 删除来访出入申请信息
     *
     * @param id 来访出入申请主键
     * @return 结果
     */
     int deleteVisitById(String id);

     AmountValueChartDto getAmountValueChart(int year);
     Map getStatisticsSummary();
}

package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.vo.req.ReportAuditReq;

/**
 * 工单管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IReportService {
    /**
     * 查询工单管理
     *
     * @param id 工单管理主键
     * @return 工单管理
     */
    public Report selectReportById(String id);

    /**
     * 查询工单管理列表
     *
     * @param report 工单管理
     * @return 工单管理集合
     */
    List<Report> selectReportList(Report report);

    /**
     * 新增工单管理
     *
     * @param report 工单管理
     * @return 结果
     */
    public int insertReport(Report report);

    /**
     * 修改工单管理
     *
     * @param report 工单管理
     * @return 结果
     */
    public int updateReport(Report report);

    /**
     * 批量删除工单管理
     *
     * @param ids 需要删除的工单管理主键集合
     * @return 结果
     */
    public int deleteReportByIds(String[] ids);

    /**
     * 删除工单管理信息
     *
     * @param id 工单管理主键
     * @return 结果
     */
    public int deleteReportById(String id);

    AjaxResult auditReport(ReportAuditReq reportAuditReq);
}

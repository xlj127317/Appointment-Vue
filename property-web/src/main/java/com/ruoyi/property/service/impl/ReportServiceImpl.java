package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.mapper.*;
import com.ruoyi.property.vo.req.ReportAuditReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.service.IReportService;

import javax.annotation.Resource;

/**
 * 工单管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Resource
    private FurnishMapper furnishMapper;

    @Resource
    private RepairMapper repairMapper;

    @Resource
    private ThingOutMapper thingOutMapper;

    @Resource
    private VisitMapper visitMapper;

    /**
     * 查询工单管理
     *
     * @param id 工单管理主键
     * @return 工单管理
     */
    @Override
    public Report selectReportById(String id) {
        return reportMapper.selectReportById(id);
    }

    /**
     * 查询工单管理列表
     *
     * @param report 工单管理
     * @return 工单管理
     */
    @Override
    public List<Report> selectReportList(Report report) {
        return reportMapper.selectReportList(report);
    }

    /**
     * 新增工单管理
     *
     * @param report 工单管理
     * @return 结果
     */
    @Override
    public int insertReport(Report report) {
        report.setId(PkeyGenerator.getUniqueString());
        report.setCreateTime(DateUtils.getNowDate());
        return reportMapper.insertReport(report);
    }

    /**
     * 修改工单管理
     *
     * @param report 工单管理
     * @return 结果
     */
    @Override
    public int updateReport(Report report) {
        return reportMapper.updateReport(report);
    }

    /**
     * 批量删除工单管理
     *
     * @param ids 需要删除的工单管理主键
     * @return 结果
     */
    @Override
    public int deleteReportByIds(String[] ids) {
        return reportMapper.deleteReportByIds(ids);
    }

    /**
     * 删除工单管理信息
     *
     * @param id 工单管理主键
     * @return 结果
     */
    @Override
    public int deleteReportById(String id) {
        return reportMapper.deleteReportById(id);
    }

    @Override
    public AjaxResult auditReport(ReportAuditReq reportAuditReq) {
        AjaxResult ajaxResult = new AjaxResult();
        if (StrUtil.equals(reportAuditReq.getTypeId(), ReportTypeEnum.FURNISH.getValue())) {
            int furnishAudit = furnishMapper.updateAuditStatus(reportAuditReq.getId(), reportAuditReq.getAuditStatus());
            ajaxResult.put("furnishAudit", furnishAudit);
        }
        if (StrUtil.equals(reportAuditReq.getTypeId(), ReportTypeEnum.THING_OUT.getValue())) {
            int thingAudit = thingOutMapper.updateAudit(reportAuditReq.getId(), reportAuditReq.getAuditStatus());
            ajaxResult.put("thingAudit", thingAudit);
        }
        if (StrUtil.equals(reportAuditReq.getTypeId(), ReportTypeEnum.REPAIR.getValue())) {
            int repairAudit = repairMapper.updateAudit(reportAuditReq.getId(), reportAuditReq.getAuditStatus());
            ajaxResult.put("repairAudit", repairAudit);
        }
        if (StrUtil.equals(reportAuditReq.getTypeId(), ReportTypeEnum.VISIT.getValue())) {
            int visitAudit = visitMapper.updateAudit(reportAuditReq.getId(), reportAuditReq.getAuditStatus());
            ajaxResult.put("visitAudit", visitAudit);
        }
        reportAuditReq.setAuditTime(DateUtils.getNowDate());
        int reportAudit = reportMapper.updateAuditStatus(reportAuditReq);
        ajaxResult.put("reportAudit", reportAudit);

        return ajaxResult;
    }
}

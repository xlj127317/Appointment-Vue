package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ReportMapper;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.service.IReportService;

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
}

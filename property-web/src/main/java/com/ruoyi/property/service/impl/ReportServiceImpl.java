package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.mapper.*;
import com.ruoyi.property.vo.req.ReportAuditReq;
import com.ruoyi.system.mapper.SysUserMapper;

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
    @Resource
    private ReportMapper reportMapper;

    @Resource
    private FurnishMapper furnishMapper;

    @Resource
    private RepairMapper repairMapper;

    @Resource
    private ThingOutMapper thingOutMapper;

    @Resource
    private VisitMapper visitMapper;

    @Resource
    private ParkReserveMapper parkReserveMapper;

    @Resource
    private SysUserMapper sysUserMapper;

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
        List<Report> list = reportMapper.selectReportList(report);
        for (Report rep : list) {
            SysUser createUser = sysUserMapper.selectUserById(rep.getCreateId());
            if (ObjectUtil.isNull(createUser)) {
                throw new ServiceException("无该对象：" + rep.getCreateId());
            }
            if (StrUtil.isNotBlank(rep.getAuditUserId())) {
                SysUser auditUser = sysUserMapper.selectUserById(rep.getAuditUserId());
                if (ObjectUtil.isNull(auditUser)) {
                    throw new ServiceException("无该对象：" + rep.getCreateId());
                }
                rep.setAuditUserId(auditUser.getNickName());
            }
            rep.setCreateId(createUser.getNickName());
        }
        return list;
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
        String typeId = reportAuditReq.getTypeId();
        String id = reportAuditReq.getId();
        Date nowDate = DateUtils.getNowDate();
        if (StrUtil.equals(typeId, ReportTypeEnum.FURNISH.getValue())) {
            int furnishAudit = furnishMapper.updateAuditStatus(id, reportAuditReq.getAuditStatus());
            ajaxResult.put("furnishAudit", furnishAudit);
        }
        if (StrUtil.equals(typeId, ReportTypeEnum.THING_OUT.getValue())) {
            int thingAudit = thingOutMapper.updateAudit(id, reportAuditReq.getAuditStatus());
            ajaxResult.put("thingAudit", thingAudit);
        }
        if (StrUtil.equals(typeId, ReportTypeEnum.REPAIR.getValue())) {
            int repairAudit = repairMapper.updateAudit(id, reportAuditReq.getAuditStatus());
            ajaxResult.put("repairAudit", repairAudit);
        }
        if (StrUtil.equals(typeId, ReportTypeEnum.VISIT.getValue())) {
            int visitAudit = visitMapper.updateAudit(id, reportAuditReq.getAuditStatus());
            ajaxResult.put("visitAudit", visitAudit);
        }
        if (StrUtil.equals(typeId, ReportTypeEnum.PARK_RESERVE.getValue())) {
            int parkReserveAudit = parkReserveMapper.updateAudit(id, reportAuditReq.getAuditStatus());
            ajaxResult.put("parkReserveAudit", parkReserveAudit);
        }
        reportAuditReq.setAuditTime(nowDate);
        int reportAudit = reportMapper.updateAuditStatus(reportAuditReq);
        ajaxResult.put("reportAudit", reportAudit);
        return AjaxResult.success(ajaxResult);
    }
}

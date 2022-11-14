package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.dto.AggregatedSumDto;
import com.ruoyi.property.dto.NamedAggregatedSumDto;
import com.ruoyi.property.dto.AmountValueChartDto;
import com.ruoyi.property.mapper.ReportMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.VisitMapper;
import com.ruoyi.property.domain.Visit;
import com.ruoyi.property.service.VisitService;

import javax.annotation.Resource;

/**
 * 来访出入申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class VisitServiceImpl implements VisitService {
    @Resource
    private VisitMapper visitMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private ReportMapper reportMapper;

    /**
     * 查询来访出入申请
     *
     * @param id 来访出入申请主键
     * @return 来访出入申请
     */
    @Override
    public Visit selectVisitById(String id) {
        return visitMapper.selectVisitById(id);
    }

    /**
     * 查询来访出入申请列表
     *
     * @param visit 来访出入申请
     * @return 来访出入申请
     */
    @Override
    public List<Visit> selectVisitList(Visit visit) {
        List<Visit> visits = visitMapper.selectVisitList(visit);
        for (Visit vis : visits) {
            String nickName = sysUserMapper.nickNameById(vis.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + vis.getCreateId(), 201);
            }
            if (StrUtil.isNotBlank(vis.getAuditId())) {
                String auditNickName = sysUserMapper.nickNameById(vis.getAuditId());
                if (StrUtil.isBlank(auditNickName)) {
                    throw new ServiceException("无此创建人：" + vis.getAuditId(), 201);
                }
                vis.setAuditId(auditNickName);
            }
            vis.setCreateId(nickName);
        }
        return visits;
    }

    /**
     * 新增来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
    @Override
    public int insertVisit(Visit visit) {
        Date nowDate = DateUtils.getNowDate();
        visit.setId(PkeyGenerator.getUniqueString());
        visit.setApplicantTime(nowDate);
        visit.setCreateTime(DateUtils.getNowDate());
        if (insertReport(visit, nowDate) != 1) {
            throw new ServiceException("添加工单失败");
        }
        return visitMapper.insertVisit(visit);
    }

    /**
     * 修改来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
    @Override
    public int updateVisit(Visit visit) {
        return visitMapper.updateVisit(visit);
    }

    /**
     * 批量删除来访出入申请
     *
     * @param ids 需要删除的来访出入申请主键
     * @return 结果
     */
    @Override
    public int deleteVisitByIds(String[] ids) {
        return visitMapper.deleteVisitByIds(ids);
    }

    /**
     * 删除来访出入申请信息
     *
     * @param id 来访出入申请主键
     * @return 结果
     */
    @Override
    public int deleteVisitById(String id) {
        return visitMapper.deleteVisitById(id);
    }

    /**
     * 添加工单信息
     *
     * @param visit   来访出入实体
     * @param nowDate 当前时间
     * @return int
     */
    private int insertReport(Visit visit, Date nowDate) {
        Report report = new Report();
        report.setId(visit.getId());
        report.setTypeId(ReportTypeEnum.VISIT.getValue());
        report.setReportContent(visit.getVisitName());
        report.setAuditStatus(0);
        report.setCreateTime(nowDate);
        report.setCreateId(visit.getCreateId());
        return reportMapper.insertReport(report);
    }

    @Override
    public AmountValueChartDto getAmountValueChart(int year) {
        List<AggregatedSumDto> byMonth = visitMapper.getAmountGroupByMonth(year);
        List<AggregatedSumDto> byQuarter = visitMapper.getAmountGroupByQuarter(year);
        return new AmountValueChartDto.Builder()
                .addMonths(byMonth)
                .addQuarters(byQuarter)
                .build();
    }

    @Override
    public Map getStatisticsSummary() {
        return visitMapper.getStatisticsSummary();
    }
}

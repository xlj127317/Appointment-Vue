package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.mapper.ReportMapper;
import com.ruoyi.property.service.RepairService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.RepairMapper;
import com.ruoyi.property.domain.Repair;

import javax.annotation.Resource;


/**
 * 物业报修申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class RepairServiceImpl implements RepairService {
    @Resource
    private RepairMapper repairMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询物业报修申请
     *
     * @param id 物业报修申请主键
     * @return 物业报修申请
     */
    @Override
    public Repair selectRepairById(String id) {
        return repairMapper.selectRepairById(id);
    }

    /**
     * 查询物业报修申请列表
     *
     * @param repair 物业报修申请
     * @return 物业报修申请
     */
    @Override
    public List<Repair> selectRepairList(Repair repair) {
        List<Repair> list = repairMapper.selectRepairList(repair);
        for (Repair rep : list) {
            String nickName = sysUserMapper.nickNameById(rep.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + repair.getCreateId(), 201);
            }
            if (StrUtil.isNotBlank(rep.getAuditId())) {
                String auditNickName = sysUserMapper.nickNameById(rep.getAuditId());
                if (StrUtil.isBlank(auditNickName)) {
                    throw new ServiceException("无此创建人：" + rep.getAuditId(), 201);
                }
                rep.setAuditId(auditNickName);
            }
            rep.setCreateId(nickName);
        }
        return list;
    }

    /**
     * 新增物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
    @Override
    public int insertRepair(Repair repair) {
        Date nowDate = DateUtils.getNowDate();
        repair.setId(PkeyGenerator.getUniqueString());
        repair.setApplicantTime(nowDate);
        repair.setCreateTime(DateUtils.getNowDate());
        if (insertReport(repair, nowDate) != 1) {
            throw new ServiceException("添加工单失败");
        }
        return repairMapper.insertRepair(repair);
    }

    /**
     * 修改物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
    @Override
    public int updateRepair(Repair repair) {
        return repairMapper.updateRepair(repair);
    }

    /**
     * 批量删除物业报修申请
     *
     * @param ids 需要删除的物业报修申请主键
     * @return 结果
     */
    @Override
    public int deleteRepairByIds(String[] ids) {
        return repairMapper.deleteRepairByIds(ids);
    }

    /**
     * 删除物业报修申请信息
     *
     * @param id 物业报修申请主键
     * @return 结果
     */
    @Override
    public int deleteRepairById(String id) {
        return repairMapper.deleteRepairById(id);
    }

    /**
     * 添加工单信息
     *
     * @param repair  物业报修实体
     * @param nowDate 当前时间
     * @return int
     */
    private int insertReport(Repair repair, Date nowDate) {
        Report report = new Report();
        report.setId(repair.getId());
        report.setTypeId(ReportTypeEnum.REPAIR.getValue());
        report.setReportContent(repair.getRepairContent());
        report.setAuditStatus(0);
        report.setCreateTime(nowDate);
        report.setCreateId(repair.getCreateId());
        return reportMapper.insertReport(report);
    }
}

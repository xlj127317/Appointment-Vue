package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.mapper.ReportMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ThingOutMapper;
import com.ruoyi.property.domain.ThingOut;
import com.ruoyi.property.service.ThingOutService;

import javax.annotation.Resource;

/**
 * 物品出入申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class ThingOutServiceImpl implements ThingOutService {
    @Resource
    private ThingOutMapper thingOutMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询物品出入申请
     *
     * @param id 物品出入申请主键
     * @return 物品出入申请
     */
    @Override
    public ThingOut selectThingOutById(String id) {
        return thingOutMapper.selectThingOutById(id);
    }

    /**
     * 查询物品出入申请列表
     *
     * @param thingOut 物品出入申请
     * @return 物品出入申请
     */
    @Override
    public List<ThingOut> selectThingOutList(ThingOut thingOut) {
        List<ThingOut> list = thingOutMapper.selectThingOutList(thingOut);
        for (ThingOut out : list) {
            SysUser sysUser = sysUserMapper.selectUserById(out.getCreateId());
            if (ObjectUtil.isNull(sysUser)) {
                throw new ServiceException("无该对象：" + out.getCreateId());
            }
            if (StrUtil.isNotBlank(out.getAuditId())) {
                SysUser auditUser = sysUserMapper.selectUserById(out.getAuditId());
                if (ObjectUtil.isNull(auditUser)) {
                    throw new ServiceException("无该对象：" + out.getAuditId());
                }
                out.setAuditId(auditUser.getNickName());
            }
            out.setCreateId(sysUser.getNickName());
        }
        return list;
    }

    /**
     * 新增物品出入申请
     *
     * @param thingOut 物品出入申请
     * @return 结果
     */
    @Override
    public int insertThingOut(ThingOut thingOut) {
        Date nowDate = DateUtils.getNowDate();
        thingOut.setId(PkeyGenerator.getUniqueString());
        thingOut.setApplicantTime(nowDate);
        thingOut.setCreateTime(DateUtils.getNowDate());
        if (insertReport(thingOut, nowDate) != 1) {
            throw new ServiceException("添加工单失败");
        }
        return thingOutMapper.insertThingOut(thingOut);
    }

    /**
     * 修改物品出入申请
     *
     * @param thingOut 物品出入申请
     * @return 结果
     */
    @Override
    public int updateThingOut(ThingOut thingOut) {
        return thingOutMapper.updateThingOut(thingOut);
    }

    /**
     * 批量删除物品出入申请
     *
     * @param ids 需要删除的物品出入申请主键
     * @return 结果
     */
    @Override
    public int deleteThingOutByIds(String[] ids) {
        return thingOutMapper.deleteThingOutByIds(ids);
    }

    /**
     * 删除物品出入申请信息
     *
     * @param id 物品出入申请主键
     * @return 结果
     */
    @Override
    public int deleteThingOutById(String id) {
        return thingOutMapper.deleteThingOutById(id);
    }

    /**
     * 添加工单信息
     *
     * @param thingOut 物品出入实体
     * @param nowDate  当前时间
     * @return int
     */
    private int insertReport(ThingOut thingOut, Date nowDate) {
        Report report = new Report();
        report.setId(thingOut.getId());
        report.setTypeId(ReportTypeEnum.THING_OUT.getValue());
        report.setReportContent(thingOut.getThing());
        report.setAuditStatus(0);
        report.setCreateTime(nowDate);
        report.setCreateId(thingOut.getCreateId());
        return reportMapper.insertReport(report);
    }
}

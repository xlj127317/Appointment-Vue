package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Repair;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ThingOutMapper;
import com.ruoyi.property.domain.ThingOut;
import com.ruoyi.property.service.ThingOutService;

/**
 * 物品出入申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class ThingOutServiceImpl implements ThingOutService {
    @Autowired
    private ThingOutMapper thingOutMapper;

    @Autowired
    private ReportMapper reportMapper;

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
        return thingOutMapper.selectThingOutList(thingOut);
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
        if (insertReport(thingOut,nowDate) != 1) {
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
     * @param nowDate 当前时间
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

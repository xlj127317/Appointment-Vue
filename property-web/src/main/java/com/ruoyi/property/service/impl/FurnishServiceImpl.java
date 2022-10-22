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
import com.ruoyi.property.service.FurnishService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.FurnishMapper;
import com.ruoyi.property.domain.Furnish;

import javax.annotation.Resource;

/**
 * 装修办理申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class FurnishServiceImpl implements FurnishService {
    @Resource
    private FurnishMapper furnishMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询装修办理申请
     *
     * @param id 装修办理申请主键
     * @return 装修办理申请
     */
    @Override
    public Furnish selectFurnishById(String id) {
        return furnishMapper.selectFurnishById(id);
    }

    /**
     * 查询装修办理申请列表
     *
     * @param furnish 装修办理申请
     * @return 装修办理申请
     */
    @Override
    public List<Furnish> selectFurnishList(Furnish furnish) {
        List<Furnish> list = furnishMapper.selectFurnishList(furnish);
        for (Furnish fu : list) {
            SysUser createUser = sysUserMapper.selectUserById(fu.getCreateId());
            if (ObjectUtil.isNull(createUser)) {
                throw new ServiceException("无该对象：" + fu.getCreateId());
            }
            if (StrUtil.isNotBlank(fu.getAuditId())) {
                SysUser auditUser = sysUserMapper.selectUserById(fu.getAuditId());
                if (ObjectUtil.isNull(auditUser)) {
                    throw new ServiceException("无该对象：" + fu.getAuditId());
                }
                fu.setAuditId(auditUser.getNickName());
            }
            fu.setCreateId(createUser.getNickName());
        }
        return list;
    }

    /**
     * 新增装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    @Override
    public int insertFurnish(Furnish furnish) {
        Date nowDate = DateUtils.getNowDate();
        furnish.setId(PkeyGenerator.getUniqueString());
        furnish.setApplicantTime(DateUtils.getNowDate());
        furnish.setCreateTime(nowDate);
        if (insertReport(furnish, nowDate) != 1) {
            throw new ServiceException("添加工单失败");
        }
        return furnishMapper.insertFurnish(furnish);
    }

    /**
     * 修改装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    @Override
    public int updateFurnish(Furnish furnish) {
        return furnishMapper.updateFurnish(furnish);
    }

    /**
     * 批量删除装修办理申请
     *
     * @param ids 需要删除的装修办理申请主键
     * @return 结果
     */
    @Override
    public int deleteFurnishByIds(String[] ids) {
        return furnishMapper.deleteFurnishByIds(ids);
    }

    /**
     * 删除装修办理申请信息
     *
     * @param id 装修办理申请主键
     * @return 结果
     */
    @Override
    public int deleteFurnishById(String id) {
        return furnishMapper.deleteFurnishById(id);
    }

    /**
     * 添加工单信息
     *
     * @param furnish 装修办理实体
     * @param nowDate 当前时间
     * @return int
     */
    private int insertReport(Furnish furnish, Date nowDate) {
        Report report = new Report();
        report.setId(furnish.getId());
        report.setTypeId(ReportTypeEnum.FURNISH.getValue());
        report.setReportContent(furnish.getProjectName());
        report.setAuditStatus(0);
        report.setCreateTime(nowDate);
        report.setCreateId(furnish.getCreateId());
        return reportMapper.insertReport(report);
    }
}

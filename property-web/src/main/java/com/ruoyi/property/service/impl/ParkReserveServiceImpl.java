package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.enums.ReportTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Furnish;
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ParkReserveMapper;
import com.ruoyi.property.domain.ParkReserve;
import com.ruoyi.property.service.ParkReserveService;

/**
 * 园区资源预约Service业务层处理
 *
 * @author wind
 * @date 2022-10-18
 */
@Service
public class ParkReserveServiceImpl implements ParkReserveService {
    @Autowired
    private ParkReserveMapper parkReserveMapper;

    @Autowired
    private ReportMapper reportMapper;

    /**
     * 查询园区资源预约
     *
     * @param id 园区资源预约主键
     * @return 园区资源预约
     */
    @Override
    public ParkReserve selectParkReserveById(String id) {
        return parkReserveMapper.selectParkReserveById(id);
    }

    /**
     * 查询园区资源预约列表
     *
     * @param parkReserve 园区资源预约
     * @return 园区资源预约
     */
    @Override
    public List<ParkReserve> selectParkReserveList(ParkReserve parkReserve) {
        return parkReserveMapper.selectParkReserveList(parkReserve);
    }

    /**
     * 新增园区资源预约
     *
     * @param parkReserve 园区资源预约
     * @return 结果
     */
    @Override
    public int insertParkReserve(ParkReserve parkReserve) {
        if (DateUtil.compare(parkReserve.getStatTime(), DateUtil.date()) <= 0) {
            throw new ServiceException("开始时间不能小于于或等于当前时间时间");
        }
        if (DateUtil.compare(parkReserve.getStatTime(), parkReserve.getStopTime()) >= 0) {
            throw new ServiceException("结束时间不能小于或等于开始时间");
        }
        Date nowDate = DateUtils.getNowDate();
        parkReserve.setId(PkeyGenerator.getUniqueString());
        parkReserve.setCreateTime(nowDate);
        if (insertReport(parkReserve, nowDate) != 1) {
            throw new ServiceException("添加工单失败");
        }
        return parkReserveMapper.insertParkReserve(parkReserve);
    }

    /**
     * 修改园区资源预约
     *
     * @param parkReserve 园区资源预约
     * @return 结果
     */
    @Override
    public int updateParkReserve(ParkReserve parkReserve) {
        return parkReserveMapper.updateParkReserve(parkReserve);
    }

    /**
     * 批量删除园区资源预约
     *
     * @param ids 需要删除的园区资源预约主键
     * @return 结果
     */
    @Override
    public int deleteParkReserveByIds(String[] ids) {
        return parkReserveMapper.deleteParkReserveByIds(ids);
    }

    /**
     * 删除园区资源预约信息
     *
     * @param id 园区资源预约主键
     * @return 结果
     */
    @Override
    public int deleteParkReserveById(String id) {
        return parkReserveMapper.deleteParkReserveById(id);
    }

    /**
     * 添加工单信息
     *
     * @param parkReserve 装修办理实体
     * @param nowDate 当前时间
     * @return int
     */
    private int insertReport(ParkReserve parkReserve, Date nowDate) {
        Report report = new Report();
        report.setId(parkReserve.getId());
        report.setTypeId(ReportTypeEnum.FURNISH.getValue());
        report.setReportContent(parkReserve.getReason());
        report.setAuditStatus(0);
        report.setCreateTime(nowDate);
        report.setCreateId(parkReserve.getCreateId());
        return reportMapper.insertReport(report);
    }
}

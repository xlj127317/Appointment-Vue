package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.date.DateUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
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
            throw new ServiceException("开始时间不能大于或等于当前时间时间");
        }
        if (DateUtil.compare(parkReserve.getStatTime(), parkReserve.getStopTime()) <= 0) {
            throw new ServiceException("结束时间不能大于或等于开始时间");
        }
        parkReserve.setId(PkeyGenerator.getUniqueString());
        parkReserve.setCreateTime(DateUtils.getNowDate());
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
}

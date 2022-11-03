package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.HydropowerMapper;
import com.ruoyi.property.domain.Hydropower;
import com.ruoyi.property.service.HydropowerService;

import javax.annotation.Resource;

/**
 * 水电统计Service业务层处理
 *
 * @author wind
 * @date 2022-11-03
 */
@Service
public class HydropowerServiceImpl implements HydropowerService {
    @Resource
    private HydropowerMapper hydropowerMapper;

    /**
     * 查询水电统计
     *
     * @param id 水电统计主键
     * @return 水电统计
     */
    @Override
    public Hydropower selectHydropowerById(String id) {
        return hydropowerMapper.selectHydropowerById(id);
    }

    /**
     * 查询水电统计列表
     *
     * @param hydropower 水电统计
     * @return 水电统计
     */
    @Override
    public List<Hydropower> selectHydropowerList(Hydropower hydropower) {
        return hydropowerMapper.selectHydropowerList(hydropower);
    }

    /**
     * 新增水电统计
     *
     * @param hydropower 水电统计
     * @return 结果
     */
    @Override
    public int insertHydropower(Hydropower hydropower) {
        hydropower.setCreateTime(DateUtils.getNowDate());
        return hydropowerMapper.insertHydropower(hydropower);
    }

    /**
     * 修改水电统计
     *
     * @param hydropower 水电统计
     * @return 结果
     */
    @Override
    public int updateHydropower(Hydropower hydropower) {
        return hydropowerMapper.updateHydropower(hydropower);
    }

    /**
     * 批量删除水电统计
     *
     * @param ids 需要删除的水电统计主键
     * @return 结果
     */
    @Override
    public int deleteHydropowerByIds(String[] ids) {
        return hydropowerMapper.deleteHydropowerByIds(ids);
    }

    /**
     * 删除水电统计信息
     *
     * @param id 水电统计主键
     * @return 结果
     */
    @Override
    public int deleteHydropowerById(String id) {
        return hydropowerMapper.deleteHydropowerById(id);
    }
}

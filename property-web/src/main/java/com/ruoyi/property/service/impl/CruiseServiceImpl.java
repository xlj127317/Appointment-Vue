package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.service.CruiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.CruiseMapper;
import com.ruoyi.property.domain.Cruise;

/**
 * 巡航管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-24
 */
@Service
public class CruiseServiceImpl implements CruiseService {
    @Autowired
    private CruiseMapper cruiseMapper;

    /**
     * 查询巡航管理
     *
     * @param id 巡航管理主键
     * @return 巡航管理
     */
    @Override
    public Cruise selectCruiseById(String id) {
        return cruiseMapper.selectCruiseById(id);
    }

    /**
     * 查询巡航管理列表
     *
     * @param cruise 巡航管理
     * @return 巡航管理
     */
    @Override
    public List<Cruise> selectCruiseList(Cruise cruise) {
        return cruiseMapper.selectCruiseList(cruise);
    }

    /**
     * 新增巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    @Override
    public int insertCruise(Cruise cruise) {
        cruise.setId(PkeyGenerator.getUniqueString());
        cruise.setCreateTime(DateUtils.getNowDate());
        return cruiseMapper.insertCruise(cruise);
    }

    /**
     * 修改巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    @Override
    public int updateCruise(Cruise cruise) {
        return cruiseMapper.updateCruise(cruise);
    }

    /**
     * 批量删除巡航管理
     *
     * @param ids 需要删除的巡航管理主键
     * @return 结果
     */
    @Override
    public int deleteCruiseByIds(String[] ids) {
        return cruiseMapper.deleteCruiseByIds(ids);
    }

    /**
     * 删除巡航管理信息
     *
     * @param id 巡航管理主键
     * @return 结果
     */
    @Override
    public int deleteCruiseById(String id) {
        return cruiseMapper.deleteCruiseById(id);
    }
}

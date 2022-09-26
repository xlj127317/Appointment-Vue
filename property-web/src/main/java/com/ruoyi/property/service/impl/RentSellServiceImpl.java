package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.RentSellMapper;
import com.ruoyi.property.domain.RentSell;
import com.ruoyi.property.service.IRentSellService;

/**
 * 租售管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class RentSellServiceImpl implements IRentSellService {
    @Autowired
    private RentSellMapper rentSellMapper;

    /**
     * 查询租售管理
     *
     * @param id 租售管理主键
     * @return 租售管理
     */
    @Override
    public RentSell selectRentSellById(String id) {
        return rentSellMapper.selectRentSellById(id);
    }

    /**
     * 查询租售管理列表
     *
     * @param rentSell 租售管理
     * @return 租售管理
     */
    @Override
    public List<RentSell> selectRentSellList(RentSell rentSell) {
        return rentSellMapper.selectRentSellList(rentSell);
    }

    /**
     * 新增租售管理
     *
     * @param rentSell 租售管理
     * @return 结果
     */
    @Override
    public int insertRentSell(RentSell rentSell) {
        rentSell.setId(PkeyGenerator.getUniqueString());
        rentSell.setCreateTime(DateUtils.getNowDate());
        return rentSellMapper.insertRentSell(rentSell);
    }

    /**
     * 修改租售管理
     *
     * @param rentSell 租售管理
     * @return 结果
     */
    @Override
    public int updateRentSell(RentSell rentSell) {
        return rentSellMapper.updateRentSell(rentSell);
    }

    /**
     * 批量删除租售管理
     *
     * @param ids 需要删除的租售管理主键
     * @return 结果
     */
    @Override
    public int deleteRentSellByIds(String[] ids) {
        return rentSellMapper.deleteRentSellByIds(ids);
    }

    /**
     * 删除租售管理信息
     *
     * @param id 租售管理主键
     * @return 结果
     */
    @Override
    public int deleteRentSellById(String id) {
        return rentSellMapper.deleteRentSellById(id);
    }
}

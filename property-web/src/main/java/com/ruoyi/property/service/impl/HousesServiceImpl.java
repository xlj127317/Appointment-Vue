package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.HousesMapper;
import com.ruoyi.property.domain.Houses;
import com.ruoyi.property.service.IHousesService;

/**
 * 房屋管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class HousesServiceImpl implements IHousesService {
    @Autowired
    private HousesMapper housesMapper;

    /**
     * 查询房屋管理
     *
     * @param id 房屋管理主键
     * @return 房屋管理
     */
    @Override
    public Houses selectHousesById(String id) {
        return housesMapper.selectHousesById(id);
    }

    /**
     * 查询房屋管理列表
     *
     * @param houses 房屋管理
     * @return 房屋管理
     */
    @Override
    public List<Houses> selectHousesList(Houses houses) {
        return housesMapper.selectHousesList(houses);
    }

    /**
     * 新增房屋管理
     *
     * @param houses 房屋管理
     * @return 结果
     */
    @Override
    public int insertHouses(Houses houses) {
        houses.setId(PkeyGenerator.getUniqueString());
        houses.setCreateTime(DateUtils.getNowDate());
        return housesMapper.insertHouses(houses);
    }

    /**
     * 修改房屋管理
     *
     * @param houses 房屋管理
     * @return 结果
     */
    @Override
    public int updateHouses(Houses houses) {
        return housesMapper.updateHouses(houses);
    }

    /**
     * 批量删除房屋管理
     *
     * @param ids 需要删除的房屋管理主键
     * @return 结果
     */
    @Override
    public int deleteHousesByIds(String[] ids) {
        return housesMapper.deleteHousesByIds(ids);
    }

    /**
     * 删除房屋管理信息
     *
     * @param id 房屋管理主键
     * @return 结果
     */
    @Override
    public int deleteHousesById(String id) {
        return housesMapper.deleteHousesById(id);
    }
}

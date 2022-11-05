package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Houses;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface HousesMapper {

    /**
     * 查询房屋管理
     *
     * @param id 房屋管理主键
     * @return 房屋管理
     */
    Houses selectHousesById(String id);

    /**
     * 查询房屋管理列表
     *
     * @param houses 房屋管理
     * @return 房屋管理集合
     */
    List<Houses> selectHousesList(Houses houses);

    /**
     * 新增房屋管理
     *
     * @param houses 房屋管理
     * @return 结果
     */
    int insertHouses(Houses houses);

    /**
     * 修改房屋管理
     *
     * @param houses 房屋管理
     * @return 结果
     */
    int updateHouses(Houses houses);

    /**
     * 删除房屋管理
     *
     * @param id 房屋管理主键
     * @return 结果
     */
    int deleteHousesById(String id);

    /**
     * 批量删除房屋管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHousesByIds(String[] ids);

    List<Houses> selectHousesByNo(String housesNo);
}
package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Houses;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface HousesMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Houses record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Houses record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Houses selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Houses record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Houses record);

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
}
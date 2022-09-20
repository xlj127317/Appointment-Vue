package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Assets;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author PG
 */
@Mapper
public interface AssetsMapper {
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
    int insert(Assets record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Assets record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Assets selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Assets record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Assets record);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return int
     */
    int updateByIds(String[] ids);

    List<Assets> queryHousesList(Assets assets);

    /**
     * 详情
     *
     * @param id id
     * @return asset
     */
    Assets selectOne(String id);
}
package com.ruoyi.backstage.mapper;

import com.ruoyi.backstage.domain.Assets;
import org.apache.ibatis.annotations.Mapper;

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
}
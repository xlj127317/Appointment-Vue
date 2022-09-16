package com.ruoyi.property.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.property.domain.ActuallyType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author PG
 */
@Mapper
public interface ActuallyTypeMapper extends BaseMapper<ActuallyType> {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ActuallyType record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ActuallyType record);

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
    int insert(ActuallyType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ActuallyType selectByPrimaryKey(String id);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ActuallyType record);
}
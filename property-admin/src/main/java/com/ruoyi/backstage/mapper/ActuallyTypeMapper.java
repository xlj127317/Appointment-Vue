package com.ruoyi.backstage.mapper;

import com.ruoyi.backstage.domain.ActuallyType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActuallyTypeMapper {
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
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ActuallyType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ActuallyType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ActuallyType record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ActuallyType record);
}
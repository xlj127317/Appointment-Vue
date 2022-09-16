package com.hcx.property.mapper;

import com.hcx.property.domain.Refund;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wind
 */
@Mapper
public interface RefundMapper {
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
    int insert(Refund record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Refund record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Refund selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Refund record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Refund record);
}
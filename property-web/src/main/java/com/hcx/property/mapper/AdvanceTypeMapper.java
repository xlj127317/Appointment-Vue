package com.hcx.property.mapper;

import com.hcx.property.domain.AdvanceType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wind
 */
@Mapper
public interface AdvanceTypeMapper {
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
    int insert(AdvanceType record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(AdvanceType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    AdvanceType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(AdvanceType record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(AdvanceType record);
}
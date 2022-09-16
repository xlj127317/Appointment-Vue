package com.hcx.property.mapper;

import com.hcx.property.domain.Advance;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wind
 */
@Mapper
public interface AdvanceMapper {
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
    int insert(Advance record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Advance record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Advance selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Advance record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Advance record);
}
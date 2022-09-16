package com.hcx.property.mapper;

import com.hcx.property.domain.OffsetType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wind
 */
@Mapper
public interface OffsetTypeMapper {
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
    int insert(OffsetType record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(OffsetType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    OffsetType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(OffsetType record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(OffsetType record);
}
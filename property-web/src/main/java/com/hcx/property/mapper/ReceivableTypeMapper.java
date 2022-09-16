package com.hcx.property.mapper;

import com.hcx.property.domain.ReceivableType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReceivableTypeMapper {
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
    int insert(ReceivableType record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ReceivableType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ReceivableType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ReceivableType record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ReceivableType record);
}
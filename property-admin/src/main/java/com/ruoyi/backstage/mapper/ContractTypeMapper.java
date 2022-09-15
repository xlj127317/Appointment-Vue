package com.ruoyi.backstage.mapper;

import com.ruoyi.backstage.domain.ContractType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wind
 */
@Mapper
public interface ContractTypeMapper {
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
    int insert(ContractType record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ContractType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ContractType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ContractType record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(ContractType record);
}
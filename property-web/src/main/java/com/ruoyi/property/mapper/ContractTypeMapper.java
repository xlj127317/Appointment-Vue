package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.ContractType;
import org.apache.ibatis.annotations.Mapper;

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
}
package com.ruoyi.backstage.mapper;

import com.ruoyi.backstage.domain.DepositAdvance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepositAdvanceMapper {
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
    int insert(DepositAdvance record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(DepositAdvance record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    DepositAdvance selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(DepositAdvance record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(DepositAdvance record);
}
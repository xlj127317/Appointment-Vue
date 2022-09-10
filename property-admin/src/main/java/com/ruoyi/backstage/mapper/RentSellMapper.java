package com.ruoyi.backstage.mapper;

import com.ruoyi.backstage.domain.RentSell;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RentSellMapper {
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
    int insert(RentSell record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(RentSell record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    RentSell selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(RentSell record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(RentSell record);
}
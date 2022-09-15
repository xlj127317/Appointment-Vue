package com.ruoyi.backstage.mapper;

import com.ruoyi.backstage.domain.Report;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
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
    int insert(Report record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Report record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Report selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Report record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Report record);
}
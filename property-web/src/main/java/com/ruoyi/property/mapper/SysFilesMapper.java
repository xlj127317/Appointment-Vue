package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.SysFiles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysFilesMapper {
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
    int insertSelective(SysFiles record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    SysFiles selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(SysFiles record);

    /**
     * 查询id集合
     *
     * @param ids id 集合
     * @return list
     */
    List<SysFiles> queryByIds(List<String> ids);

    int updateByIds(List<String> ids);
}
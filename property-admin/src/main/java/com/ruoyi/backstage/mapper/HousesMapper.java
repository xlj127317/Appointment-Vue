package com.ruoyi.backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.backstage.domain.Actually;
import com.ruoyi.backstage.domain.Houses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author PG
 */
@Mapper
public interface HousesMapper extends BaseMapper<Houses> {
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
    int insert(Houses record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Houses record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Houses selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Houses record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Houses record);

    /**
     * 批量逻辑删除
     *
     * @param ids id 集合
     * @return int
     */
    int updateByIds(@Param("ids") String[] ids);

    /**
     * 分页查询列表
     *
     * @param houses 列表条件
     * @return list
     */
    List<Houses> queryHousesList(Houses houses);
}
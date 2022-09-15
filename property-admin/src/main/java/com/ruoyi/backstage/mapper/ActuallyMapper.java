package com.ruoyi.backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.backstage.domain.Actually;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author PG
 */
@Mapper
public interface ActuallyMapper extends BaseMapper<Actually> {
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
    int insertSelective(Actually record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Actually selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Actually record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Actually record);

    /**
     * 批量删除
     *
     * @param ids id集合
     * @return int
     */
    int updateByIds(@Param("ids") String... ids);

    /**
     * 分页条件查询
     *
     * @param actually 实体
     * @return list
     */
    List<Actually> selectActuallyList(Actually actually);
}
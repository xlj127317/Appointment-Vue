package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.ActuallyType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author PG
 */
@Mapper
public interface ActuallyTypeMapper {
    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(ActuallyType record);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ActuallyType record);

    /**
     * 批量逻辑删除
     *
     * @param ids id集合
     * @return int
     */
    int updateByIds(String[] ids);

    /**
     * 分页条件查询
     *
     * @param actuallyType 分页条件
     * @return list
     */
    List<ActuallyType> selectActuallyList(ActuallyType actuallyType);

    ActuallyType selectOne(String id);
}
package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Cruise;
import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface CruiseMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Cruise selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Cruise record);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Cruise record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Cruise record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Cruise record);

    /**
     * 查询巡航管理
     *
     * @param id 巡航管理主键
     * @return 巡航管理
     */
    Cruise selectCruiseById(String id);

    /**
     * 查询巡航管理列表
     *
     * @param cruise 巡航管理
     * @return 巡航管理集合
     */
    List<Cruise> selectCruiseList(Cruise cruise);

    /**
     * 新增巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    int insertCruise(Cruise cruise);

    /**
     * 修改巡航管理
     *
     * @param cruise 巡航管理
     * @return 结果
     */
    int updateCruise(Cruise cruise);

    /**
     * 删除巡航管理
     *
     * @param id 巡航管理主键
     * @return 结果
     */
    int deleteCruiseById(String id);

    /**
     * 批量删除巡航管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCruiseByIds(String[] ids);
}
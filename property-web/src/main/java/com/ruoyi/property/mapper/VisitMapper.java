package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Visit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VisitMapper {
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
    int insertSelective(Visit record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Visit selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Visit record);

    /**
     * 查询来访出入申请
     *
     * @param id 来访出入申请主键
     * @return 来访出入申请
     */
    public Visit selectVisitById(String id);

    /**
     * 查询来访出入申请列表
     *
     * @param visit 来访出入申请
     * @return 来访出入申请集合
     */
    List<Visit> selectVisitList(Visit visit);

    /**
     * 新增来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
    int insertVisit(Visit visit);

    /**
     * 修改来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
    int updateVisit(Visit visit);

    /**
     * 删除来访出入申请
     *
     * @param id 来访出入申请主键
     * @return 结果
     */
    int deleteVisitById(String id);

    /**
     * 批量删除来访出入申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteVisitByIds(String[] ids);
}
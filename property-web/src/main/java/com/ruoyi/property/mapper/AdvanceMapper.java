package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Advance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdvanceMapper {
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
    int insertSelective(Advance record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Advance selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Advance record);

    /**
     * 查询预收管理
     *
     * @param id 预收管理主键
     * @return 预收管理
     */
    public Advance selectAdvanceById(String id);

    /**
     * 查询预收管理列表
     *
     * @param advance 预收管理
     * @return 预收管理集合
     */
    List<Advance> selectAdvanceList(Advance advance);

    /**
     * 新增预收管理
     *
     * @param advance 预收管理
     * @return 结果
     */
    int insertAdvance(Advance advance);

    /**
     * 修改预收管理
     *
     * @param advance 预收管理
     * @return 结果
     */
    int updateAdvance(Advance advance);

    /**
     * 删除预收管理
     *
     * @param id 预收管理主键
     * @return 结果
     */
    int deleteAdvanceById(String id);

    /**
     * 批量删除预收管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteAdvanceByIds(String[] ids);

}
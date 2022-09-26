package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Receivable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReceivableMapper {
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
    int insertSelective(Receivable record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Receivable selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Receivable record);

    /**
     * 查询应收管理
     *
     * @param id 应收管理主键
     * @return 应收管理
     */
    Receivable selectReceivableById(String id);

    /**
     * 查询应收管理列表
     *
     * @param receivable 应收管理
     * @return 应收管理集合
     */
    List<Receivable> selectReceivableList(Receivable receivable);

    /**
     * 新增应收管理
     *
     * @param receivable 应收管理
     * @return 结果
     */
    int insertReceivable(Receivable receivable);

    /**
     * 修改应收管理
     *
     * @param receivable 应收管理
     * @return 结果
     */
    int updateReceivable(Receivable receivable);

    /**
     * 删除应收管理
     *
     * @param id 应收管理主键
     * @return 结果
     */
    int deleteReceivableById(String id);

    /**
     * 批量删除应收管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteReceivableByIds(String[] ids);
}
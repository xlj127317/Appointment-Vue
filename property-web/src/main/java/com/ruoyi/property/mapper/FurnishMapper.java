package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Furnish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FurnishMapper {
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
    int insertSelective(Furnish record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Furnish selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Furnish record);

    /**
     * 查询装修办理申请
     *
     * @param id 装修办理申请主键
     * @return 装修办理申请
     */
    Furnish selectFurnishById(String id);

    /**
     * 查询装修办理申请列表
     *
     * @param furnish 装修办理申请
     * @return 装修办理申请集合
     */
    List<Furnish> selectFurnishList(Furnish furnish);

    /**
     * 新增装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    int insertFurnish(Furnish furnish);

    /**
     * 修改装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    int updateFurnish(Furnish furnish);

    /**
     * 删除装修办理申请
     *
     * @param id 装修办理申请主键
     * @return 结果
     */
    int deleteFurnishById(String id);

    /**
     * 批量删除装修办理申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteFurnishByIds(String[] ids);

    /**
     * 审核状态
     *
     * @param id          id
     * @param auditStatus 状态
     * @return int
     */
    int updateAuditStatus(@Param("id") String id, @Param("auditStatus") Integer auditStatus);
}
package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Repair;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepairMapper {
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
    int insertSelective(Repair record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Repair selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Repair record);

    /**
     * 查询物业报修申请
     *
     * @param id 物业报修申请主键
     * @return 物业报修申请
     */
    Repair selectRepairById(String id);

    /**
     * 查询物业报修申请列表
     *
     * @param repair 物业报修申请
     * @return 物业报修申请集合
     */
    List<Repair> selectRepairList(Repair repair);

    /**
     * 新增物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
    int insertRepair(Repair repair);

    /**
     * 修改物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
    int updateRepair(Repair repair);

    /**
     * 删除物业报修申请
     *
     * @param id 物业报修申请主键
     * @return 结果
     */
    int deleteRepairById(String id);

    /**
     * 批量删除物业报修申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRepairByIds(String[] ids);
}
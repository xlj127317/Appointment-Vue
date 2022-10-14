package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.ThingOut;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ThingOutMapper {
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
    int insertSelective(ThingOut record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ThingOut selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ThingOut record);

    /**
     * 查询物品出入申请
     *
     * @param id 物品出入申请主键
     * @return 物品出入申请
     */
    ThingOut selectThingOutById(String id);

    /**
     * 查询物品出入申请列表
     *
     * @param thingOut 物品出入申请
     * @return 物品出入申请集合
     */
    List<ThingOut> selectThingOutList(ThingOut thingOut);

    /**
     * 新增物品出入申请
     *
     * @param thingOut 物品出入申请
     * @return 结果
     */
    int insertThingOut(ThingOut thingOut);

    /**
     * 修改物品出入申请
     *
     * @param thingOut 物品出入申请
     * @return 结果
     */
    int updateThingOut(ThingOut thingOut);

    /**
     * 删除物品出入申请
     *
     * @param id 物品出入申请主键
     * @return 结果
     */
    int deleteThingOutById(String id);

    /**
     * 批量删除物品出入申请
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteThingOutByIds(String[] ids);

    /**
     * 审核状态
     *
     * @param id          id
     * @param auditStatus 状态
     * @return int
     */
    int updateAudit(@Param("id") String id, @Param("auditStatus") Integer auditStatus);
}
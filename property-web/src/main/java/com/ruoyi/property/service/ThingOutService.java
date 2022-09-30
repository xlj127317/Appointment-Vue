package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.ThingOut;

/**
 * 物品出入申请Service接口
 *
 * @author wind
 * @date 2022-09-29
 */
public interface ThingOutService {
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
     * 批量删除物品出入申请
     *
     * @param ids 需要删除的物品出入申请主键集合
     * @return 结果
     */
     int deleteThingOutByIds(String[] ids);

    /**
     * 删除物品出入申请信息
     *
     * @param id 物品出入申请主键
     * @return 结果
     */
     int deleteThingOutById(String id);
}

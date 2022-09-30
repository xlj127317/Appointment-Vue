package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ThingOutMapper;
import com.ruoyi.property.domain.ThingOut;
import com.ruoyi.property.service.ThingOutService;

/**
 * 物品出入申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class ThingOutServiceImpl implements ThingOutService {
    @Autowired
    private ThingOutMapper thingOutMapper;

    /**
     * 查询物品出入申请
     *
     * @param id 物品出入申请主键
     * @return 物品出入申请
     */
    @Override
    public ThingOut selectThingOutById(String id) {
        return thingOutMapper.selectThingOutById(id);
    }

    /**
     * 查询物品出入申请列表
     *
     * @param thingOut 物品出入申请
     * @return 物品出入申请
     */
    @Override
    public List<ThingOut> selectThingOutList(ThingOut thingOut) {
        return thingOutMapper.selectThingOutList(thingOut);
    }

    /**
     * 新增物品出入申请
     *
     * @param thingOut 物品出入申请
     * @return 结果
     */
    @Override
    public int insertThingOut(ThingOut thingOut) {
        thingOut.setId(PkeyGenerator.getUniqueString());
        thingOut.setCreateTime(DateUtils.getNowDate());
        return thingOutMapper.insertThingOut(thingOut);
    }

    /**
     * 修改物品出入申请
     *
     * @param thingOut 物品出入申请
     * @return 结果
     */
    @Override
    public int updateThingOut(ThingOut thingOut) {
        return thingOutMapper.updateThingOut(thingOut);
    }

    /**
     * 批量删除物品出入申请
     *
     * @param ids 需要删除的物品出入申请主键
     * @return 结果
     */
    @Override
    public int deleteThingOutByIds(String[] ids) {
        return thingOutMapper.deleteThingOutByIds(ids);
    }

    /**
     * 删除物品出入申请信息
     *
     * @param id 物品出入申请主键
     * @return 结果
     */
    @Override
    public int deleteThingOutById(String id) {
        return thingOutMapper.deleteThingOutById(id);
    }
}

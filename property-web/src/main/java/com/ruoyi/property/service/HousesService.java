package com.ruoyi.property.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.domain.Houses;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/15 14:32
 **/
public interface HousesService {

    /**
     * 添加
     *
     * @param houses 房屋
     * @return AjaxResult
     */
    AjaxResult insertHouses(Houses houses);

    /**
     * 详情
     *
     * @param id id
     * @return AjaxResult
     */
    AjaxResult queryById(String id);


    /**
     * 更新数据
     *
     * @param houses 更新条件
     * @return AjaxResult
     */
    AjaxResult updateHousesById(Houses houses);

    /**
     * 批量逻辑删除
     *
     * @param ids ids
     * @return AjaxResult
     */
    int deleteById(String[] ids);

    /**
     * 分页条件查询
     *
     * @param houses 房屋
     * @return AjaxResult
     */
    List<Houses> queryList(Houses houses);

}

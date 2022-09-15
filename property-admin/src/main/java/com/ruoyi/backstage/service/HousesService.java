package com.ruoyi.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.backstage.domain.Actually;
import com.ruoyi.backstage.domain.Houses;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/15 14:32
 **/
public interface HousesService extends IService<Houses> {

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
     * 批量逻辑删除
     *
     * @param ids ids
     * @return AjaxResult
     */
    AjaxResult deleteById(String[] ids);

    /**
     * 分页条件查询
     *
     * @param houses 房屋
     * @return AjaxResult
     */
    List<Houses> queryList(Houses houses);
}

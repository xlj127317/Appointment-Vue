package com.ruoyi.property.service;

import com.ruoyi.property.domain.Actually;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/09 14:30
 **/
public interface ActuallyService {

    /**
     * 添加
     *
     * @param actually entity
     * @return ajaxResult
     */
    AjaxResult insertActually(Actually actually);

    /**
     * 详情
     *
     * @param id 主键
     * @return result
     */
    AjaxResult queryById(String id);

    /**
     * 批量删除
     *
     * @param ids ids
     * @return ajaxResult
     */
    int deleteById(String[] ids);

    /**
     * 分页
     *
     * @param actually 分页对象
     * @return List<Actually>
     */
    List<Actually> queryList(Actually actually);

    AjaxResult updateById(Actually actually);
}
package com.hcx.property.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hcx.property.domain.Actually;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/09 14:30
 **/
public interface ActuallyService extends IService<Actually> {

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
    AjaxResult deleteById(String[] ids);

    /**
     * 分页
     *
     * @param actually 分页对象
     * @return List<Actually>
     */
    List<Actually> queryList(Actually actually);
}

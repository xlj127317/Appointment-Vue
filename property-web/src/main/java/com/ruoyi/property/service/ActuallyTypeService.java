package com.ruoyi.property.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.domain.Actually;
import com.ruoyi.property.domain.ActuallyType;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 17:32
 **/
public interface ActuallyTypeService extends IService<ActuallyType> {
    /**
     * 新增
     *
     * @param actuallyType 应收类型
     * @return result
     */
    AjaxResult insertActuallyType(ActuallyType actuallyType);

    /**
     * 详情
     *
     * @param id id
     * @return result
     */
    AjaxResult queryById(String id);

    /**
     * 逻辑删除
     *
     * @param ids id
     * @return result
     */
    AjaxResult deleteById(String[] ids);

    /**
     * 分页条件查询
     *
     * @param actuallyType 分页条件
     * @return list
     */
    List<ActuallyType> queryList(ActuallyType actuallyType);
}

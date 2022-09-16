package com.ruoyi.property.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.property.domain.Assets;
import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 14:13
 **/
public interface AssetsService extends IService<Assets> {
    /**
     * 添加
     *
     * @param assets entity
     * @return ajaxResult
     */
    AjaxResult insertAssets(Assets assets);

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
     * @param assets 分页对象
     * @return List<Assets>
     */
    List<Assets> queryList(Assets assets);
}

package com.ruoyi.property.service;

import com.ruoyi.property.domain.AssetsType;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/20 17:26
 **/
 public interface IAssetsTypeService {
    /**
     * 查询资产类别管理
     *
     * @param id 资产类别管理主键
     * @return 资产类别管理
     */
     AssetsType selectAssetsTypeById(String id);

    /**
     * 查询资产类别管理列表
     *
     * @param assetsType 资产类别管理
     * @return 资产类别管理集合
     */
     List<AssetsType> selectAssetsTypeList(AssetsType assetsType);

    /**
     * 新增资产类别管理
     *
     * @param assetsType 资产类别管理
     * @return 结果
     */
     int insertAssetsType(AssetsType assetsType);

    /**
     * 修改资产类别管理
     *
     * @param assetsType 资产类别管理
     * @return 结果
     */
     int updateAssetsType(AssetsType assetsType);

    /**
     * 批量删除资产类别管理
     *
     * @param ids 需要删除的资产类别管理主键集合
     * @return 结果
     */
     int deleteAssetsTypeByIds(String[] ids);

    /**
     * 删除资产类别管理信息
     *
     * @param id 资产类别管理主键
     * @return 结果
     */
     int deleteAssetsTypeById(String id);
}

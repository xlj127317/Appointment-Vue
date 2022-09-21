package com.ruoyi.property.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import com.ruoyi.property.domain.AssetsType;
import com.ruoyi.property.mapper.AssetsTypeMapper;
import com.ruoyi.property.service.IAssetsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/20 17:28
 **/
@Service
public class AssetsTypeServiceImpl implements IAssetsTypeService {

    @Autowired
    private AssetsTypeMapper assetsTypeMapper;

    /**
     * 查询资产类别管理
     *
     * @param id 资产类别管理主键
     * @return 资产类别管理
     */
    @Override
    public AssetsType selectAssetsTypeById(String id) {
        return assetsTypeMapper.selectAssetsTypeById(id);
    }

    /**
     * 查询资产类别管理列表
     *
     * @param assetsType 资产类别管理
     * @return 资产类别管理
     */
    @Override
    public List<AssetsType> selectAssetsTypeList(AssetsType assetsType) {
        return assetsTypeMapper.selectAssetsTypeList(assetsType);
    }

    /**
     * 新增资产类别管理
     *
     * @param assetsType 资产类别管理
     * @return 结果
     */
    @Override
    public int insertAssetsType(AssetsType assetsType) {
        assetsType.setId(IdUtils.fastSimpleUUID());
        assetsType.setCreateTime(DateUtils.getNowDate());
        return assetsTypeMapper.insertAssetsType(assetsType);
    }

    /**
     * 修改资产类别管理
     *
     * @param assetsType 资产类别管理
     * @return 结果
     */
    @Override
    public int updateAssetsType(AssetsType assetsType) {
        return assetsTypeMapper.updateAssetsType(assetsType);
    }

    /**
     * 批量删除资产类别管理
     *
     * @param ids 需要删除的资产类别管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetsTypeByIds(String[] ids) {
        return assetsTypeMapper.deleteAssetsTypeByIds(ids);
    }

    /**
     * 删除资产类别管理信息
     *
     * @param id 资产类别管理主键
     * @return 结果
     */
    @Override
    public int deleteAssetsTypeById(String id) {
        return assetsTypeMapper.deleteAssetsTypeById(id);
    }

}

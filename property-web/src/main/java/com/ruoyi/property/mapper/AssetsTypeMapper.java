package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.AssetsType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AssetsTypeMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(AssetsType record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(AssetsType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    AssetsType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(AssetsType record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(AssetsType record);

    /**
     * 查询资产类别管理
     *
     * @param id 资产类别管理主键
     * @return 资产类别管理
     */
    public AssetsType selectAssetsTypeById(String id);

    /**
     * 查询资产类别管理列表
     *
     * @param assetsType 资产类别管理
     * @return 资产类别管理集合
     */
    public List<AssetsType> selectAssetsTypeList(AssetsType assetsType);

    /**
     * 新增资产类别管理
     *
     * @param assetsType 资产类别管理
     * @return 结果
     */
    public int insertAssetsType(AssetsType assetsType);

    /**
     * 修改资产类别管理
     *
     * @param assetsType 资产类别管理
     * @return 结果
     */
    public int updateAssetsType(AssetsType assetsType);

    /**
     * 删除资产类别管理
     *
     * @param id 资产类别管理主键
     * @return 结果
     */
    public int deleteAssetsTypeById(String id);

    /**
     * 批量删除资产类别管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAssetsTypeByIds(String[] ids);

}
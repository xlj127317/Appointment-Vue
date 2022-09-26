package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.RentSell;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentSellMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(String id);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(RentSell record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    RentSell selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(RentSell record);

    /**
     * 查询租售管理
     *
     * @param id 租售管理主键
     * @return 租售管理
     */
    RentSell selectRentSellById(String id);

    /**
     * 查询租售管理列表
     *
     * @param rentSell 租售管理
     * @return 租售管理集合
     */
    List<RentSell> selectRentSellList(RentSell rentSell);

    /**
     * 新增租售管理
     *
     * @param rentSell 租售管理
     * @return 结果
     */
    int insertRentSell(RentSell rentSell);

    /**
     * 修改租售管理
     *
     * @param rentSell 租售管理
     * @return 结果
     */
    int updateRentSell(RentSell rentSell);

    /**
     * 删除租售管理
     *
     * @param id 租售管理主键
     * @return 结果
     */
    int deleteRentSellById(String id);

    /**
     * 批量删除租售管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRentSellByIds(String[] ids);

}
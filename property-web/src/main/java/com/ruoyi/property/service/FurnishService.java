package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Furnish;

/**
 * 装修办理申请Service接口
 *
 * @author wind
 * @date 2022-09-29
 */
public interface FurnishService {
    /**
     * 查询装修办理申请
     *
     * @param id 装修办理申请主键
     * @return 装修办理申请
     */
    Furnish selectFurnishById(String id);

    /**
     * 查询装修办理申请列表
     *
     * @param furnish 装修办理申请
     * @return 装修办理申请集合
     */
    List<Furnish> selectFurnishList(Furnish furnish);

    /**
     * 新增装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    int insertFurnish(Furnish furnish);

    /**
     * 修改装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    int updateFurnish(Furnish furnish);

    /**
     * 批量删除装修办理申请
     *
     * @param ids 需要删除的装修办理申请主键集合
     * @return 结果
     */
    int deleteFurnishByIds(String[] ids);

    /**
     * 删除装修办理申请信息
     *
     * @param id 装修办理申请主键
     * @return 结果
     */
    int deleteFurnishById(String id);
}

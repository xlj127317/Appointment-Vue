package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.service.FurnishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.FurnishMapper;
import com.ruoyi.property.domain.Furnish;

/**
 * 装修办理申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class FurnishServiceImpl implements FurnishService {
    @Autowired
    private FurnishMapper furnishMapper;

    /**
     * 查询装修办理申请
     *
     * @param id 装修办理申请主键
     * @return 装修办理申请
     */
    @Override
    public Furnish selectFurnishById(String id) {
        return furnishMapper.selectFurnishById(id);
    }

    /**
     * 查询装修办理申请列表
     *
     * @param furnish 装修办理申请
     * @return 装修办理申请
     */
    @Override
    public List<Furnish> selectFurnishList(Furnish furnish) {
        return furnishMapper.selectFurnishList(furnish);
    }

    /**
     * 新增装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    @Override
    public int insertFurnish(Furnish furnish) {
        furnish.setId(PkeyGenerator.getUniqueString());
        furnish.setCreateTime(DateUtils.getNowDate());
        return furnishMapper.insertFurnish(furnish);
    }

    /**
     * 修改装修办理申请
     *
     * @param furnish 装修办理申请
     * @return 结果
     */
    @Override
    public int updateFurnish(Furnish furnish) {
        return furnishMapper.updateFurnish(furnish);
    }

    /**
     * 批量删除装修办理申请
     *
     * @param ids 需要删除的装修办理申请主键
     * @return 结果
     */
    @Override
    public int deleteFurnishByIds(String[] ids) {
        return furnishMapper.deleteFurnishByIds(ids);
    }

    /**
     * 删除装修办理申请信息
     *
     * @param id 装修办理申请主键
     * @return 结果
     */
    @Override
    public int deleteFurnishById(String id) {
        return furnishMapper.deleteFurnishById(id);
    }
}

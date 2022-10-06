package com.ruoyi.property.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.dto.AdvanceListInput;

/**
 * 预收管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IAdvanceService {
    /**
     * 查询预收管理
     *
     * @param id 预收管理主键
     * @return 预收管理
     */
    public Advance selectAdvanceById(String id);

    /**
     * 查询预收管理列表
     *
     * @param input 预收管理
     * @return 预收管理集合
     */
    public List<Advance> selectAdvanceList(AdvanceListInput input);

    /**
     * 新增预收管理
     *
     * @param advance 预收管理
     * @return 结果
     */
    public int insertAdvance(Advance advance);

    /**
     * 修改预收管理
     *
     * @param advance 预收管理
     * @return 结果
     */
    public int updateAdvance(Advance advance);

    /**
     * 批量删除预收管理
     *
     * @param ids 需要删除的预收管理主键集合
     * @return 结果
     */
    public int deleteAdvanceByIds(String[] ids);

    /**
     * 删除预收管理信息
     *
     * @param id 预收管理主键
     * @return 结果
     */
    public int deleteAdvanceById(String id);

    boolean exists(String contractId, String paymentTypeId, Date date);
}

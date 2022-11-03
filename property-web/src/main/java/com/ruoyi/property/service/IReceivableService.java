package com.ruoyi.property.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.property.domain.Receivable;
import com.ruoyi.property.dto.ReceivableListInput;

/**
 * 应收管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IReceivableService {
    /**
     * 查询应收管理
     *
     * @param id 应收管理主键
     * @return 应收管理
     */
    public Receivable selectReceivableById(String id);

    /**
     * 查询应收管理列表
     *
     * @param input 应收管理
     * @return 应收管理集合
     */
    public List<Receivable> selectReceivableList(ReceivableListInput input);

    /**
     * 新增应收管理
     *
     * @param receivable 应收管理
     * @return 结果
     */
    public int insertReceivable(Receivable receivable);

    /**
     * 修改应收管理
     *
     * @param receivable 应收管理
     * @return 结果
     */
    public int updateReceivable(Receivable receivable);

    /**
     * 批量删除应收管理
     *
     * @param ids 需要删除的应收管理主键集合
     * @return 结果
     */
    public int deleteReceivableByIds(String[] ids);

    /**
     * 删除应收管理信息
     *
     * @param id 应收管理主键
     * @return 结果
     */
    public int deleteReceivableById(String id);

    boolean exists(String contractId, String paymentTypeId, Date date);

    Receivable findOne(Map params);
}

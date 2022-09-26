package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.PaymentType;

/**
 * 租售类型Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IPaymentTypeService {
    /**
     * 查询租售类型
     *
     * @param id 租售类型主键
     * @return 租售类型
     */
    public PaymentType selectPaymentTypeById(String id);

    /**
     * 查询租售类型列表
     *
     * @param paymentType 租售类型
     * @return 租售类型集合
     */
    public List<PaymentType> selectPaymentTypeList(PaymentType paymentType);

    /**
     * 新增租售类型
     *
     * @param paymentType 租售类型
     * @return 结果
     */
    public int insertPaymentType(PaymentType paymentType);

    /**
     * 修改租售类型
     *
     * @param paymentType 租售类型
     * @return 结果
     */
    public int updatePaymentType(PaymentType paymentType);

    /**
     * 批量删除租售类型
     *
     * @param ids 需要删除的租售类型主键集合
     * @return 结果
     */
    public int deletePaymentTypeByIds(String[] ids);

    /**
     * 删除租售类型信息
     *
     * @param id 租售类型主键
     * @return 结果
     */
    public int deletePaymentTypeById(String id);
}

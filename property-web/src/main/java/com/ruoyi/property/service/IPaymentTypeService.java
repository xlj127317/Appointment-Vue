package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.PaymentType;

/**
 * 款项类型Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IPaymentTypeService {
    /**
     * 查询款项类型
     *
     * @param id 款项类型主键
     * @return 款项类型
     */
    public PaymentType selectPaymentTypeById(String id);

    /**
     * 查询款项类型列表
     *
     * @param paymentType 款项类型
     * @return 款项类型集合
     */
    public List<PaymentType> selectPaymentTypeList(PaymentType paymentType);

    /**
     * 新增款项类型
     *
     * @param paymentType 款项类型
     * @return 结果
     */
    public int insertPaymentType(PaymentType paymentType);

    /**
     * 修改款项类型
     *
     * @param paymentType 款项类型
     * @return 结果
     */
    public int updatePaymentType(PaymentType paymentType);

    /**
     * 批量删除款项类型
     *
     * @param ids 需要删除的款项类型主键集合
     * @return 结果
     */
    public int deletePaymentTypeByIds(String[] ids);

    /**
     * 删除款项类型信息
     *
     * @param id 款项类型主键
     * @return 结果
     */
    public int deletePaymentTypeById(String id);
}

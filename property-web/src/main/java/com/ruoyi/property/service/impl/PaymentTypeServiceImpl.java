package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.PaymentTypeMapper;
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.service.IPaymentTypeService;

/**
 * 款项类型Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class PaymentTypeServiceImpl implements IPaymentTypeService {
    @Autowired
    private PaymentTypeMapper paymentTypeMapper;

    /**
     * 查询款项类型
     *
     * @param id 款项类型主键
     * @return 款项类型
     */
    @Override
    public PaymentType selectPaymentTypeById(String id) {
        return paymentTypeMapper.selectPaymentTypeById(id);
    }

    /**
     * 查询款项类型列表
     *
     * @param paymentType 款项类型
     * @return 款项类型
     */
    @Override
    public List<PaymentType> selectPaymentTypeList(PaymentType paymentType) {
        return paymentTypeMapper.selectPaymentTypeList(paymentType);
    }

    /**
     * 新增款项类型
     *
     * @param paymentType 款项类型
     * @return 结果
     */
    @Override
    public int insertPaymentType(PaymentType paymentType) {
        paymentType.setId(PkeyGenerator.getUniqueString());
        paymentType.setCreateTime(DateUtils.getNowDate());
        return paymentTypeMapper.insertPaymentType(paymentType);
    }

    /**
     * 修改款项类型
     *
     * @param paymentType 款项类型
     * @return 结果
     */
    @Override
    public int updatePaymentType(PaymentType paymentType) {
        return paymentTypeMapper.updatePaymentType(paymentType);
    }

    /**
     * 批量删除款项类型
     *
     * @param ids 需要删除的款项类型主键
     * @return 结果
     */
    @Override
    public int deletePaymentTypeByIds(String[] ids) {
        return paymentTypeMapper.deletePaymentTypeByIds(ids);
    }

    /**
     * 删除款项类型信息
     *
     * @param id 款项类型主键
     * @return 结果
     */
    @Override
    public int deletePaymentTypeById(String id) {
        return paymentTypeMapper.deletePaymentTypeById(id);
    }
}

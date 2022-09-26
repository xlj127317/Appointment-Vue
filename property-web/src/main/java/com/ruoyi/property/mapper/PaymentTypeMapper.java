package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.PaymentType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PaymentTypeMapper {
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
    int insertSelective(PaymentType record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    PaymentType selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(PaymentType record);

    /**
     * 查询款项类型
     *
     * @param id 款项类型主键
     * @return 款项类型
     */
    PaymentType selectPaymentTypeById(String id);

    /**
     * 查询款项类型列表
     *
     * @param paymentType 款项类型
     * @return 款项类型集合
     */
    List<PaymentType> selectPaymentTypeList(PaymentType paymentType);

    /**
     * 新增款项类型
     *
     * @param paymentType 款项类型
     * @return 结果
     */
    int insertPaymentType(PaymentType paymentType);

    /**
     * 修改款项类型
     *
     * @param paymentType 款项类型
     * @return 结果
     */
    int updatePaymentType(PaymentType paymentType);

    /**
     * 删除款项类型
     *
     * @param id 款项类型主键
     * @return 结果
     */
    int deletePaymentTypeById(String id);

    /**
     * 批量删除款项类型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deletePaymentTypeByIds(String[] ids);
}
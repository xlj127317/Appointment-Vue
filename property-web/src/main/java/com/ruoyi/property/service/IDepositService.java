package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Deposit;

/**
 * 押金Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IDepositService {
    /**
     * 查询押金
     *
     * @param id 押金主键
     * @return 押金
     */
    public Deposit selectDepositById(String id);

    /**
     * 查询押金列表
     *
     * @param deposit 押金
     * @return 押金集合
     */
    public List<Deposit> selectDepositList(Deposit deposit);

    /**
     * 新增押金
     *
     * @param deposit 押金
     * @return 结果
     */
    public int insertDeposit(Deposit deposit);

    /**
     * 修改押金
     *
     * @param deposit 押金
     * @return 结果
     */
    public int updateDeposit(Deposit deposit);

    /**
     * 批量删除押金
     *
     * @param ids 需要删除的押金主键集合
     * @return 结果
     */
    public int deleteDepositByIds(String[] ids);

    /**
     * 删除押金信息
     *
     * @param id 押金主键
     * @return 结果
     */
    public int deleteDepositById(String id);
}

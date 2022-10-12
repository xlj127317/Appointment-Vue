package com.ruoyi.property.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.DepositMapper;
import com.ruoyi.property.domain.Deposit;
import com.ruoyi.property.service.IDepositService;

/**
 * 押金Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class DepositServiceImpl implements IDepositService {
    @Autowired
    private DepositMapper depositMapper;

    /**
     * 查询押金
     *
     * @param id 押金主键
     * @return 押金
     */
    @Override
    public Deposit selectDepositById(String id) {
        return depositMapper.selectDepositById(id);
    }

    /**
     * 查询押金列表
     *
     * @param deposit 押金
     * @return 押金
     */
    @Override
    public List<Deposit> selectDepositList(Deposit deposit) {
        return depositMapper.selectDepositList(deposit);
    }

    /**
     * 新增押金
     *
     * @param deposit 押金
     * @return 结果
     */
    @Override
    public int insertDeposit(Deposit deposit) {
        deposit.setId(PkeyGenerator.getUniqueString());
        deposit.setCreateTime(DateUtils.getNowDate());
        return depositMapper.insertDeposit(deposit);
    }

    /**
     * 修改押金
     *
     * @param deposit 押金
     * @return 结果
     */
    @Override
    public int updateDeposit(Deposit deposit) {
        deposit.setUpdateTime(DateUtils.getNowDate());
        return depositMapper.updateDeposit(deposit);
    }

    /**
     * 批量删除押金
     *
     * @param ids 需要删除的押金主键
     * @return 结果
     */
    @Override
    public int deleteDepositByIds(String[] ids) {
        return depositMapper.deleteDepositByIds(ids);
    }

    /**
     * 删除押金信息
     *
     * @param id 押金主键
     * @return 结果
     */
    @Override
    public int deleteDepositById(String id) {
        return depositMapper.deleteDepositById(id);
    }

    public Deposit get(Map params)
    {
        return depositMapper.get(params);
    }
}

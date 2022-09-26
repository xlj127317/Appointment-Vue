package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ContractMapper;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.service.IContractService;

/**
 * 合同管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class ContractServiceImpl implements IContractService {
    @Autowired
    private ContractMapper contractMapper;

    /**
     * 查询合同管理
     *
     * @param id 合同管理主键
     * @return 合同管理
     */
    @Override
    public Contract selectContractById(String id) {
        return contractMapper.selectContractById(id);
    }

    /**
     * 查询合同管理列表
     *
     * @param contract 合同管理
     * @return 合同管理
     */
    @Override
    public List<Contract> selectContractList(Contract contract) {
        return contractMapper.selectContractList(contract);
    }

    /**
     * 新增合同管理
     *
     * @param contract 合同管理
     * @return 结果
     */
    @Override
    public int insertContract(Contract contract) {
        contract.setId(PkeyGenerator.getUniqueString());
        contract.setCreateTime(DateUtils.getNowDate());
        return contractMapper.insertContract(contract);
    }

    /**
     * 修改合同管理
     *
     * @param contract 合同管理
     * @return 结果
     */
    @Override
    public int updateContract(Contract contract) {
        return contractMapper.updateContract(contract);
    }

    /**
     * 批量删除合同管理
     *
     * @param ids 需要删除的合同管理主键
     * @return 结果
     */
    @Override
    public int deleteContractByIds(String[] ids) {
        return contractMapper.deleteContractByIds(ids);
    }

    /**
     * 删除合同管理信息
     *
     * @param id 合同管理主键
     * @return 结果
     */
    @Override
    public int deleteContractById(String id) {
        return contractMapper.deleteContractById(id);
    }
}

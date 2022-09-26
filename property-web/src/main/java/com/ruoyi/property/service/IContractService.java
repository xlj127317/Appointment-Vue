package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Contract;

/**
 * 合同管理Service接口
 *
 * @author wind
 * @date 2022-09-21
 */
public interface IContractService {
    /**
     * 查询合同管理
     *
     * @param id 合同管理主键
     * @return 合同管理
     */
    public Contract selectContractById(String id);

    /**
     * 查询合同管理列表
     *
     * @param contract 合同管理
     * @return 合同管理集合
     */
    public List<Contract> selectContractList(Contract contract);

    /**
     * 新增合同管理
     *
     * @param contract 合同管理
     * @return 结果
     */
    public int insertContract(Contract contract);

    /**
     * 修改合同管理
     *
     * @param contract 合同管理
     * @return 结果
     */
    public int updateContract(Contract contract);

    /**
     * 批量删除合同管理
     *
     * @param ids 需要删除的合同管理主键集合
     * @return 结果
     */
    public int deleteContractByIds(String[] ids);

    /**
     * 删除合同管理信息
     *
     * @param id 合同管理主键
     * @return 结果
     */
    public int deleteContractById(String id);
}

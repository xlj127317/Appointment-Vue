package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.ContractConfig;

/**
 * 合同配置Service接口
 *
 * @author wind
 * @date 2022-10-10
 */
public interface ContractConfigService {
    /**
     * 查询合同配置
     *
     * @param id 合同配置主键
     * @return 合同配置
     */
    public ContractConfig selectContractConfigById(String id);

    /**
     * 查询合同配置列表
     *
     * @param contractConfig 合同配置
     * @return 合同配置集合
     */
    public List<ContractConfig> selectContractConfigList(ContractConfig contractConfig);

    /**
     * 新增合同配置
     *
     * @param contractConfig 合同配置
     * @return 结果
     */
    public int insertContractConfig(ContractConfig contractConfig);

    /**
     * 修改合同配置
     *
     * @param contractConfig 合同配置
     * @return 结果
     */
    public int updateContractConfig(ContractConfig contractConfig);

    /**
     * 批量删除合同配置
     *
     * @param ids 需要删除的合同配置主键集合
     * @return 结果
     */
    public int deleteContractConfigByIds(String[] ids);

    /**
     * 删除合同配置信息
     *
     * @param id 合同配置主键
     * @return 结果
     */
    public int deleteContractConfigById(String id);
}

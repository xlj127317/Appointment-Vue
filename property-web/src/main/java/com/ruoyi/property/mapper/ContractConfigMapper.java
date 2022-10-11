package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.ContractConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContractConfigMapper {
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
    int insertSelective(ContractConfig record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    ContractConfig selectByPrimaryKey(String id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(ContractConfig record);

    /**
     * 查询合同配置
     *
     * @param id 合同配置主键
     * @return 合同配置
     */
    ContractConfig selectContractConfigById(String id);

    /**
     * 查询合同配置列表
     *
     * @param contractConfig 合同配置
     * @return 合同配置集合
     */
    List<ContractConfig> selectContractConfigList(ContractConfig contractConfig);

    /**
     * 新增合同配置
     *
     * @param contractConfig 合同配置
     * @return 结果
     */
    int insertContractConfig(ContractConfig contractConfig);

    /**
     * 修改合同配置
     *
     * @param contractConfig 合同配置
     * @return 结果
     */
    int updateContractConfig(ContractConfig contractConfig);

    /**
     * 删除合同配置
     *
     * @param id 合同配置主键
     * @return 结果
     */
    int deleteContractConfigById(String id);

    /**
     * 批量删除合同配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteContractConfigByIds(String[] ids);
}
package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.Contract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ContractMapper {
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
    int insertSelective(Contract record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Contract selectByPrimaryKey(String id);

    Contract find(Map params);
    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Contract record);

    /**
     * 查询合同管理
     *
     * @param id 合同管理主键
     * @return 合同管理
     */
    Contract selectContractById(String id);

    /**
     * 查询合同管理列表
     *
     * @param contract 合同管理
     * @return 合同管理集合
     */
    List<Contract> selectContractList(Contract contract);

    /**
     * 新增合同管理
     *
     * @param contract 合同管理
     * @return 结果
     */
    int insertContract(Contract contract);

    /**
     * 修改合同管理
     *
     * @param contract 合同管理
     * @return 结果
     */
    int updateContract(Contract contract);

    /**
     * 删除合同管理
     *
     * @param id 合同管理主键
     * @return 结果
     */
    int deleteContractById(String id);

    /**
     * 批量删除合同管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteContractByIds(String[] ids);

    List<Contract> checkContract(String[] id);
}
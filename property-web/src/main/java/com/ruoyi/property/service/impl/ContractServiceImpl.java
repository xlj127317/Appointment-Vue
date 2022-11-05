package com.ruoyi.property.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ContractMapper;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.service.IContractService;

import javax.annotation.Resource;

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

    @Resource
    private SysUserMapper sysUserMapper;

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

    public Contract findOne(Map params) {
        params.put("limit", 1);
        return contractMapper.find(params);
    }

    /**
     * 查询合同管理列表
     *
     * @param contract 合同管理
     * @return 合同管理
     */
    @Override
    public List<Contract> selectContractList(Contract contract) {
        List<Contract> list = contractMapper.selectContractList(contract);
        for (Contract con : list) {
            String nickName = sysUserMapper.nickNameById(con.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + con.getCreateId(), 201);
            }
            con.setCreateId(nickName);
        }
        return list;
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

    public QueryBuilder newQuery() {
        return new QueryBuilderImpl(this);
    }

    private static class QueryBuilderImpl implements QueryBuilder {
        private Map params = new HashMap();
        private IContractService contractService;

        public QueryBuilderImpl(IContractService contractService) {
            this.contractService = contractService;
        }

        @Override
        public QueryBuilder id(String id) {
            params.put("id", id);
            return this;
        }

        @Override
        public QueryBuilder ownerId(String ownerId) {
            params.put("ownerId", ownerId);
            return this;
        }

        @Override
        public QueryBuilder lockMode(SqlLockMode lockMode) {
            params.put("lockMode", lockMode);
            return this;
        }

        @Override
        public QueryBuilder limit(int limit) {
            params.put("limit", limit);
            return this;
        }

        @Override
        public Contract findOne() {
            limit(1);
            return contractService.findOne(params);
        }
    }
}

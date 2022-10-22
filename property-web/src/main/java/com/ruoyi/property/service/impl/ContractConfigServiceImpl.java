package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.mapper.ContractMapper;
import com.ruoyi.property.service.ContractConfigService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ContractConfigMapper;
import com.ruoyi.property.domain.ContractConfig;

import javax.annotation.Resource;

/**
 * 合同配置Service业务层处理
 *
 * @author wind
 * @date 2022-10-10
 */
@Service
public class ContractConfigServiceImpl implements ContractConfigService {
    @Resource
    private ContractConfigMapper contractConfigMapper;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询合同配置
     *
     * @param id 合同配置主键
     * @return 合同配置
     */
    @Override
    public ContractConfig selectContractConfigById(String id) {
        return contractConfigMapper.selectContractConfigById(id);
    }

    /**
     * 查询合同配置列表
     *
     * @param contractConfig 合同配置
     * @return 合同配置
     */
    @Override
    public List<ContractConfig> selectContractConfigList(ContractConfig contractConfig) {
        List<ContractConfig> list = contractConfigMapper.selectContractConfigList(contractConfig);
        for (ContractConfig config : list) {
            SysUser sysUser = sysUserMapper.selectUserById(config.getCreateId());
            if (ObjectUtil.isNull(sysUser)) {
                throw new ServiceException("无该对象：" + config.getCreateId());
            }
            config.setCreateId(sysUser.getNickName());
        }
        return list;
    }

    /**
     * 新增合同配置
     *
     * @param contractConfig 合同配置
     * @return 结果
     */
    @Override
    public int insertContractConfig(ContractConfig contractConfig) {
        contractConfig.setId(PkeyGenerator.getUniqueString());
        contractConfig.setCreateTime(DateUtils.getNowDate());
        return contractConfigMapper.insertContractConfig(contractConfig);
    }

    /**
     * 修改合同配置
     *
     * @param contractConfig 合同配置
     * @return 结果
     */
    @Override
    public int updateContractConfig(ContractConfig contractConfig) {
        return contractConfigMapper.updateContractConfig(contractConfig);
    }

    /**
     * 批量删除合同配置
     *
     * @param ids 需要删除的合同配置主键
     * @return 结果
     */
    @Override
    public int deleteContractConfigByIds(String[] ids) {
        List<Contract> listContract = contractMapper.checkContract(ids);
        if (CollUtil.isNotEmpty(listContract)) {
            throw new ServiceException("当前合同配置已有合同在使用，不能删除");
        }
        return contractConfigMapper.deleteContractConfigByIds(ids);
    }

    /**
     * 删除合同配置信息
     *
     * @param id 合同配置主键
     * @return 结果
     */
    @Override
    public int deleteContractConfigById(String id) {
        return contractConfigMapper.deleteContractConfigById(id);
    }
}

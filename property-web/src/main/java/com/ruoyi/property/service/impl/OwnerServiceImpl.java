package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.OwnerMapper;
import com.ruoyi.property.domain.Owner;
import com.ruoyi.property.service.IOwnerService;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;

/**
 * 业主管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class OwnerServiceImpl implements IOwnerService {
    @Autowired
    private OwnerMapper ownerMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private WalletService walletService;

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 查询业主管理
     *
     * @param id 业主管理主键
     * @return 业主管理
     */
    @Override
    public Owner selectOwnerById(String id) {
        return ownerMapper.selectOwnerById(id);
    }

    /**
     * 查询业主管理列表
     *
     * @param owner 业主管理
     * @return 业主管理
     */
    @Override
    public List<Owner> selectOwnerList(Owner owner) {
        List<Owner> owners = ownerMapper.selectOwnerList(owner);
        for (Owner own : owners) {
            String nickName = sysUserMapper.nickNameById(own.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + own.getCreateId(), 201);
            }
            own.setCreateId(nickName);
        }
        return owners;
    }

    /**
     * 新增业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    @Override
    public int insertOwner(Owner owner) {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            owner.setId(PkeyGenerator.getUniqueString());
            owner.setCreateTime(DateUtils.getNowDate());

            walletService.createIfNotExists(owner.getId());

            int result = ownerMapper.insertOwner(owner);

            transactionManager.commit(transactionStatus);

            return result;
        } catch (Exception exception) {
            transactionManager.rollback(transactionStatus);
            return 0;
        }
    }

    /**
     * 修改业主管理
     *
     * @param owner 业主管理
     * @return 结果
     */
    @Override
    public int updateOwner(Owner owner) {
        return ownerMapper.updateOwner(owner);
    }

    /**
     * 批量删除业主管理
     *
     * @param ids 需要删除的业主管理主键
     * @return 结果
     */
    @Override
    public int deleteOwnerByIds(String[] ids) {
        return ownerMapper.deleteOwnerByIds(ids);
    }

    /**
     * 删除业主管理信息
     *
     * @param id 业主管理主键
     * @return 结果
     */
    @Override
    public int deleteOwnerById(String id) {
        return ownerMapper.deleteOwnerById(id);
    }
}

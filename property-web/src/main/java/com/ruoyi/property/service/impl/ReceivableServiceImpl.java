package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.dto.ReceivableListInput;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.ReceivableMapper;
import com.ruoyi.property.domain.Receivable;
import com.ruoyi.property.service.IReceivableService;

import javax.annotation.Resource;

/**
 * 应收管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class ReceivableServiceImpl implements IReceivableService {
    @Resource
    private ReceivableMapper receivableMapper;

    @Resource
    private SysUserMapper sysUserMapper;

    /**
     * 查询应收管理
     *
     * @param id 应收管理主键
     * @return 应收管理
     */
    @Override
    public Receivable selectReceivableById(String id) {
        return receivableMapper.selectReceivableById(id);
    }

    /**
     * 查询应收管理列表
     *
     * @param input 应收管理
     * @return 应收管理
     */
    @Override
    public List<Receivable> selectReceivableList(ReceivableListInput input) {
        List<Receivable> list = receivableMapper.selectReceivableList(input);
        for (Receivable rece : list) {
            SysUser sysUser = sysUserMapper.selectUserById(rece.getCreateId());
            if (ObjectUtil.isNull(sysUser)) {
                throw new ServiceException("无该对象：" + rece.getCreateId());
            }
            rece.setCreateId(sysUser.getNickName());
        }
        return list;
    }

    /**
     * 新增应收管理
     *
     * @param receivable 应收管理
     * @return 结果
     */
    @Override
    public int insertReceivable(Receivable receivable) {
        receivable.setId(PkeyGenerator.getUniqueString());
        receivable.setCreateTime(DateUtils.getNowDate());
        return receivableMapper.insertReceivable(receivable);
    }

    /**
     * 修改应收管理
     *
     * @param receivable 应收管理
     * @return 结果
     */
    @Override
    public int updateReceivable(Receivable receivable) {
        return receivableMapper.updateReceivable(receivable);
    }

    /**
     * 批量删除应收管理
     *
     * @param ids 需要删除的应收管理主键
     * @return 结果
     */
    @Override
    public int deleteReceivableByIds(String[] ids) {
        return receivableMapper.deleteReceivableByIds(ids);
    }

    /**
     * 删除应收管理信息
     *
     * @param id 应收管理主键
     * @return 结果
     */
    @Override
    public int deleteReceivableById(String id) {
        return receivableMapper.deleteReceivableById(id);
    }

    public boolean exists(String contractId, String paymentTypeId, Date date)
    {
        return receivableMapper.exists(contractId, paymentTypeId, date);
    }
}

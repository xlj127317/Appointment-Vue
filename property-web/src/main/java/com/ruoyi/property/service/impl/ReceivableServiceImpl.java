package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import cn.hutool.core.util.StrUtil;
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
        Receivable receivable = receivableMapper.selectReceivableById(id);
        String nickName = sysUserMapper.nickNameById(receivable.getCreateId());
        if (StrUtil.isBlank(nickName)) {
            throw new ServiceException("无此创建人：" + id, 201);
        }
        receivable.setCreateId(nickName);
        return receivable;
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
            String nickName = sysUserMapper.nickNameById(rece.getCreateId());
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + rece.getCreateId(), 201);
            }
            rece.setCreateId(nickName);
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

    public boolean exists(String contractId, String paymentTypeId, Date date) {
        return receivableMapper.exists(contractId, paymentTypeId, date);
    }
}

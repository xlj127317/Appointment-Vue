package com.ruoyi.property.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.dto.AdvanceListInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.AdvanceMapper;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.service.IAdvanceService;

/**
 * 预收管理Service业务层处理
 *
 * @author wind
 * @date 2022-09-21
 */
@Service
public class AdvanceServiceImpl implements IAdvanceService {
    @Autowired
    private AdvanceMapper advanceMapper;

    /**
     * 查询预收管理
     *
     * @param id 预收管理主键
     * @return 预收管理
     */
    @Override
    public Advance selectAdvanceById(String id) {
        return advanceMapper.selectAdvanceById(id);
    }

    /**
     * 查询预收管理列表
     *
     * @param input 预收管理
     * @return 预收管理
     */
    @Override
    public List<Advance> selectAdvanceList(AdvanceListInput input) {
        return advanceMapper.selectAdvanceList(input);
    }

    /**
     * 新增预收管理
     *
     * @param advance 预收管理
     * @return 结果
     */
    @Override
    public int insertAdvance(Advance advance) {
        advance.setId(PkeyGenerator.getUniqueString());
        advance.setCreateTime(DateUtils.getNowDate());
        return advanceMapper.insertAdvance(advance);
    }

    /**
     * 修改预收管理
     *
     * @param advance 预收管理
     * @return 结果
     */
    @Override
    public int updateAdvance(Advance advance) {
        return advanceMapper.updateAdvance(advance);
    }

    /**
     * 批量删除预收管理
     *
     * @param ids 需要删除的预收管理主键
     * @return 结果
     */
    @Override
    public int deleteAdvanceByIds(String[] ids) {
        return advanceMapper.deleteAdvanceByIds(ids);
    }

    /**
     * 删除预收管理信息
     *
     * @param id 预收管理主键
     * @return 结果
     */
    @Override
    public int deleteAdvanceById(String id) {
        return advanceMapper.deleteAdvanceById(id);
    }

    @Override
    public boolean exists(String contractId, String paymentTypeId, Date date) {
        return advanceMapper.checkExists(contractId, paymentTypeId, date);
    }
}

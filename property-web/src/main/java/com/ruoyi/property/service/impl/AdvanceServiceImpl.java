package com.ruoyi.property.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.dto.AdvanceListInput;
import com.ruoyi.property.mapper.AdvanceMapper;
import com.ruoyi.property.service.IAdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public QueryBuilder newQuery() {
        return new QueryBuilderImpl();
    }

    private List<Advance> select(Map params) {
        return advanceMapper.select(params);
    }

    private class QueryBuilderImpl implements QueryBuilder {
        Map params = new HashMap();

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
        public QueryBuilder limit(Integer limit) {
            params.put("limit", limit);
            return this;
        }

        @Override
        public Advance findOneOrNull() {
            limit(2);
            List<Advance> result = AdvanceServiceImpl.this.select(params);
            int count = result.size();
            if (count == 0) {
                return null;
            } else if (count > 1) {
                // TODO: do what?
            }
            return result.get(0);
        }

        @Override
        public List<Advance> find() {
            return AdvanceServiceImpl.this.select(params);
        }
    }
}

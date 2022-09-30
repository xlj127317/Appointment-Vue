package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.VisitMapper;
import com.ruoyi.property.domain.Visit;
import com.ruoyi.property.service.VisitService;

/**
 * 来访出入申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitMapper visitMapper;

    /**
     * 查询来访出入申请
     *
     * @param id 来访出入申请主键
     * @return 来访出入申请
     */
    @Override
    public Visit selectVisitById(String id) {
        return visitMapper.selectVisitById(id);
    }

    /**
     * 查询来访出入申请列表
     *
     * @param visit 来访出入申请
     * @return 来访出入申请
     */
    @Override
    public List<Visit> selectVisitList(Visit visit) {
        return visitMapper.selectVisitList(visit);
    }

    /**
     * 新增来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
    @Override
    public int insertVisit(Visit visit) {
        visit.setId(PkeyGenerator.getUniqueString());
        visit.setCreateTime(DateUtils.getNowDate());
        return visitMapper.insertVisit(visit);
    }

    /**
     * 修改来访出入申请
     *
     * @param visit 来访出入申请
     * @return 结果
     */
    @Override
    public int updateVisit(Visit visit) {
        return visitMapper.updateVisit(visit);
    }

    /**
     * 批量删除来访出入申请
     *
     * @param ids 需要删除的来访出入申请主键
     * @return 结果
     */
    @Override
    public int deleteVisitByIds(String[] ids) {
        return visitMapper.deleteVisitByIds(ids);
    }

    /**
     * 删除来访出入申请信息
     *
     * @param id 来访出入申请主键
     * @return 结果
     */
    @Override
    public int deleteVisitById(String id) {
        return visitMapper.deleteVisitById(id);
    }
}

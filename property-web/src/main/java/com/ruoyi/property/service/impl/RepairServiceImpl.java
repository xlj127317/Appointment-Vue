package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.RepairMapper;
import com.ruoyi.property.domain.Repair;


/**
 * 物业报修申请Service业务层处理
 *
 * @author wind
 * @date 2022-09-29
 */
@Service
public class RepairServiceImpl implements RepairService {
    @Autowired
    private RepairMapper repairMapper;

    /**
     * 查询物业报修申请
     *
     * @param id 物业报修申请主键
     * @return 物业报修申请
     */
    @Override
    public Repair selectRepairById(String id) {
        return repairMapper.selectRepairById(id);
    }

    /**
     * 查询物业报修申请列表
     *
     * @param repair 物业报修申请
     * @return 物业报修申请
     */
    @Override
    public List<Repair> selectRepairList(Repair repair) {
        return repairMapper.selectRepairList(repair);
    }

    /**
     * 新增物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
    @Override
    public int insertRepair(Repair repair) {
        repair.setId(PkeyGenerator.getUniqueString());
        repair.setApplicantTime(DateUtils.getNowDate());
        repair.setCreateTime(DateUtils.getNowDate());
        return repairMapper.insertRepair(repair);
    }

    /**
     * 修改物业报修申请
     *
     * @param repair 物业报修申请
     * @return 结果
     */
    @Override
    public int updateRepair(Repair repair) {
        return repairMapper.updateRepair(repair);
    }

    /**
     * 批量删除物业报修申请
     *
     * @param ids 需要删除的物业报修申请主键
     * @return 结果
     */
    @Override
    public int deleteRepairByIds(String[] ids) {
        return repairMapper.deleteRepairByIds(ids);
    }

    /**
     * 删除物业报修申请信息
     *
     * @param id 物业报修申请主键
     * @return 结果
     */
    @Override
    public int deleteRepairById(String id) {
        return repairMapper.deleteRepairById(id);
    }
}

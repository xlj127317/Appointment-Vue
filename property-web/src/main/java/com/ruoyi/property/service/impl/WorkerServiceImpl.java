package com.ruoyi.property.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.WorkerMapper;
import com.ruoyi.property.domain.Worker;
import com.ruoyi.property.service.WorkerService;

import javax.annotation.Resource;

/**
 * 用工管理Service业务层处理
 *
 * @author wind
 * @date 2022-10-11
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerMapper workerMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    /**
     * 查询用工管理
     *
     * @param id 用工管理主键
     * @return 用工管理
     */
    @Override
    public Worker selectWorkerById(String id) {
        return workerMapper.selectWorkerById(id);
    }

    /**
     * 查询用工管理列表
     *
     * @param worker 用工管理
     * @return 用工管理
     */
    @Override
    public List<Worker> selectWorkerList(Worker worker) {
        List<Worker> list = workerMapper.selectWorkerList(worker);
        for (Worker wor : list) {
            SysUser sysUser = sysUserMapper.selectUserById(wor.getCreateId());
            if (ObjectUtil.isNull(sysUser)) {
                throw new ServiceException("无该对象：" + wor.getCreateId());
            }
            wor.setCreateId(sysUser.getNickName());
        }
        return list;
    }

    /**
     * 新增用工管理
     *
     * @param worker 用工管理
     * @return 结果
     */
    @Override
    public int insertWorker(Worker worker) {
        worker.setId(PkeyGenerator.getUniqueString());
        worker.setCreateTime(DateUtils.getNowDate());
        return workerMapper.insertWorker(worker);
    }

    /**
     * 修改用工管理
     *
     * @param worker 用工管理
     * @return 结果
     */
    @Override
    public int updateWorker(Worker worker) {
        return workerMapper.updateWorker(worker);
    }

    /**
     * 批量删除用工管理
     *
     * @param ids 需要删除的用工管理主键
     * @return 结果
     */
    @Override
    public int deleteWorkerByIds(String[] ids) {
        return workerMapper.deleteWorkerByIds(ids);
    }

    /**
     * 删除用工管理信息
     *
     * @param id 用工管理主键
     * @return 结果
     */
    @Override
    public int deleteWorkerById(String id) {
        return workerMapper.deleteWorkerById(id);
    }
}

package com.ruoyi.property.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.property.mapper.WorkerMapper;
import com.ruoyi.property.domain.Worker;
import com.ruoyi.property.service.WorkerService;

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
        return workerMapper.selectWorkerList(worker);
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

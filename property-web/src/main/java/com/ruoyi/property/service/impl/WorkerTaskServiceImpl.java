package com.ruoyi.property.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.WorkerTask;
import com.ruoyi.property.mapper.WorkerTaskMapper;
import com.ruoyi.property.service.WorkerTaskService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用工任务Service业务层处理
 *
 * @author wind
 * @date 2022-10-28
 */
@Service
public class WorkerTaskServiceImpl implements WorkerTaskService {
    @Resource
    private WorkerTaskMapper workerTaskMapper;

    @Resource
    private SysUserMapper userMapper;

    /**
     * 查询用工任务
     *
     * @param id 用工任务主键
     * @return 用工任务
     */
    @Override
    public WorkerTask selectWorkerTaskById(String id) {
        return workerTaskMapper.selectWorkerTaskById(id);
    }

    /**
     * 查询用工任务列表
     *
     * @param workerTask 用工任务
     * @return 用工任务
     */
    @Override
    public List<WorkerTask> selectWorkerTaskList(WorkerTask workerTask) {
        List<WorkerTask> list = workerTaskMapper.selectWorkerTaskList(workerTask);
        for (WorkerTask task : list) {
            String createId = task.getCreateId();
            SysUser userById = userMapper.selectUserById(createId);
            SysUser userByUserName = userMapper.selectUserByUserName(task.getReceiver());
            if (ObjectUtil.isNull(userById)) {
                throw new ServiceException("无该创建人");
            }
            if (ObjectUtil.isNull(userByUserName)) {
                throw new ServiceException("无该接收人");
            }
            task.setReceiver(userByUserName.getNickName());
            task.setCreateId(userById.getNickName());
        }
        return list;
    }

    /**
     * 新增用工任务
     *
     * @param workerTask 用工任务
     * @return 结果
     */
    @Override
    public int insertWorkerTask(WorkerTask workerTask) {
        workerTask.setId(PkeyGenerator.getUniqueString());
        workerTask.setCreateTime(DateUtils.getNowDate());
        return workerTaskMapper.insertWorkerTask(workerTask);
    }

    /**
     * 修改用工任务
     *
     * @param workerTask 用工任务
     * @return 结果
     */
    @Override
    public int updateWorkerTask(WorkerTask workerTask) {
        return workerTaskMapper.updateWorkerTask(workerTask);
    }

    /**
     * 批量删除用工任务
     *
     * @param ids 需要删除的用工任务主键
     * @return 结果
     */
    @Override
    public int deleteWorkerTaskByIds(String[] ids) {
        return workerTaskMapper.deleteWorkerTaskByIds(ids);
    }

    /**
     * 删除用工任务信息
     *
     * @param id 用工任务主键
     * @return 结果
     */
    @Override
    public int deleteWorkerTaskById(String id) {
        return workerTaskMapper.deleteWorkerTaskById(id);
    }
}


package com.ruoyi.property.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.WorkerTask;
import com.ruoyi.property.mapper.WorkerTaskMapper;
import com.ruoyi.property.service.WorkerTaskService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
            String nickName = userMapper.nickNameById(createId);
            if (StrUtil.isBlank(nickName)) {
                throw new ServiceException("无此创建人：" + task.getCreateId(), 201);
            }
            String receiverName = userMapper.nickNameByUserName(task.getReceiver());
            if (StrUtil.isBlank(receiverName)) {
                throw new ServiceException("无该接收人: " + task.getReceiver());
            }
            task.setReceiver(receiverName);
            task.setCreateId(nickName);
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

    @Override
    public int updateStatusById(String id, Integer completeStatus) {
        Date nowDate = DateUtils.getNowDate();
        return workerTaskMapper.updateStatus(id, completeStatus, nowDate);
    }
}


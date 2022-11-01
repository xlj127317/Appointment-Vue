package com.ruoyi.property.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.domain.WorkerTask;

import java.util.List;

/**
 * 用工任务Service接口
 *
 * @author wind
 * @date 2022-10-28
 */
public interface WorkerTaskService {
    /**
     * 查询用工任务
     *
     * @param id 用工任务主键
     * @return 用工任务
     */
    WorkerTask selectWorkerTaskById(String id);

    /**
     * 查询用工任务列表
     *
     * @param workerTask 用工任务
     * @return 用工任务集合
     */
    List<WorkerTask> selectWorkerTaskList(WorkerTask workerTask);

    /**
     * 新增用工任务
     *
     * @param workerTask 用工任务
     * @return 结果
     */
    AjaxResult insertWorkerTask(WorkerTask workerTask);

    /**
     * 修改用工任务
     *
     * @param workerTask 用工任务
     * @return 结果
     */
    int updateWorkerTask(WorkerTask workerTask);

    /**
     * 批量删除用工任务
     *
     * @param ids 需要删除的用工任务主键集合
     * @return 结果
     */
    int deleteWorkerTaskByIds(String[] ids);

    /**
     * 删除用工任务信息
     *
     * @param id 用工任务主键
     * @return 结果
     */
    int deleteWorkerTaskById(String id);

    /**
     * 修改任务完成情况
     *
     * @param id             id
     * @param completeStatus 完成状态
     * @return ajaxResult
     */
    AjaxResult updateStatusById(String id, Integer completeStatus);
}


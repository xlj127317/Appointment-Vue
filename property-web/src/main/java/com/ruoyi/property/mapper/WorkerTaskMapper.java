package com.ruoyi.property.mapper;

import com.ruoyi.property.domain.WorkerTask;import org.apache.ibatis.annotations.Mapper;import java.util.List;

@Mapper
public interface WorkerTaskMapper {
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
    int insertWorkerTask(WorkerTask workerTask);

    /**
     * 修改用工任务
     *
     * @param workerTask 用工任务
     * @return 结果
     */
    int updateWorkerTask(WorkerTask workerTask);

    /**
     * 删除用工任务
     *
     * @param id 用工任务主键
     * @return 结果
     */
    int deleteWorkerTaskById(String id);

    /**
     * 批量删除用工任务
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWorkerTaskByIds(String[] ids);
}
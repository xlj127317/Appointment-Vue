package com.ruoyi.property.service;

import java.util.List;

import com.ruoyi.property.domain.Worker;

/**
 * 用工管理Service接口
 *
 * @author wind
 * @date 2022-10-11
 */
public interface WorkerService {
    /**
     * 查询用工管理
     *
     * @param id 用工管理主键
     * @return 用工管理
     */
    Worker selectWorkerById(String id);

    /**
     * 查询用工管理列表
     *
     * @param worker 用工管理
     * @return 用工管理集合
     */
    List<Worker> selectWorkerList(Worker worker);

    /**
     * 新增用工管理
     *
     * @param worker 用工管理
     * @return 结果
     */
    String insertWorker(Worker worker);

    /**
     * 修改用工管理
     *
     * @param worker 用工管理
     * @return 结果
     */
    int updateWorker(Worker worker);

    /**
     * 批量删除用工管理
     *
     * @param ids 需要删除的用工管理主键集合
     * @return 结果
     */
    int deleteWorkerByIds(String[] ids);

    /**
     * 删除用工管理信息
     *
     * @param id 用工管理主键
     * @return 结果
     */
    int deleteWorkerById(String id);
}

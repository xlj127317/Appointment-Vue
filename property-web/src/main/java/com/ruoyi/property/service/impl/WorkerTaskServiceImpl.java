package com.ruoyi.property.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.WorkRelation;
import com.ruoyi.property.domain.Worker;
import com.ruoyi.property.domain.WorkerTask;
import com.ruoyi.property.mapper.WorkRelationMapper;
import com.ruoyi.property.mapper.WorkerMapper;
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
     * 关联用工关联表表Mapper注入
     */
    @Resource
    private WorkRelationMapper workAndTaskMapper;

    @Resource
    private WorkerMapper workerMapper;

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
    public AjaxResult insertWorkerTask(WorkerTask workerTask) {
        AjaxResult ajaxResult = new AjaxResult();
        String id = PkeyGenerator.getUniqueString();
        Date nowDate = DateUtils.getNowDate();
        workerTask.setId(id);
        workerTask.setCreateTime(nowDate);
        String nickName = userMapper.nickNameById(workerTask.getCreateId());
        if (StrUtil.isBlank(nickName)) {
            throw new ServiceException("无此创建人：" + workerTask.getCreateId(), 201);
        }
        workerTask.setCreateName(nickName);
        int insertNum = workerTaskMapper.insertWorkerTask(workerTask);
        int insertWorkNum = insertWorkAndTask(workerTask);
        if (insertNum <= 0 && insertWorkNum <= 0) {
            ajaxResult.put(id + "：pushStatus：", "false");
        }
        if (updateWork(workerTask, nowDate) <= 0) {
            ajaxResult.put(workerTask.getWorkId() + "：updateStatus：", "false");
        }
        ajaxResult.put(id + "：pushStatus：", true);
        ajaxResult.put(workerTask.getWorkId() + "：updateStatus：", true);
        return AjaxResult.success(ajaxResult);
    }

    /**
     * 更新用工推送状态
     *
     * @param workerTask 用工任务
     * @param nowDate    推送时间
     */
    private int updateWork(WorkerTask workerTask, Date nowDate) {
        Worker worker = new Worker();
        worker.setId(workerTask.getWorkId());
        worker.setPushDate(nowDate);
        worker.setPushStatus(1);
        return workerMapper.updateWorker(worker);
    }

    /**
     * 用工任务中间表添加数据
     *
     * @param workerTask 用工任务entity
     */
    private int insertWorkAndTask(WorkerTask workerTask) {
        WorkRelation workRelation = new WorkRelation();
        workRelation.setWorkId(workerTask.getWorkId());
        workRelation.setTaskId(workerTask.getId());
        // 添加中间表数据
        return workAndTaskMapper.insert(workRelation);
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

    /**
     * 更新用工任务完成状态 - 修改用工表中的状态
     *
     * @param id             id
     * @param completeStatus 完成状态
     * @return AjaxResult
     */
    @Override
    public AjaxResult updateStatusById(String id, Integer completeStatus) {
        Date nowDate = DateUtils.getNowDate();
        AjaxResult ajaxResult = new AjaxResult();
        int updateNum = workerTaskMapper.updateStatus(id, completeStatus, nowDate);
        WorkRelation workRelation = workAndTaskMapper.selectByTaskId(id);
        if (ObjectUtil.isNull(workRelation)) {
            return AjaxResult.error("无此任务id：" + id);
        }
        String workId = workRelation.getWorkId();
        int updateFinishNum = workerMapper.updateFinishStatus(workId, completeStatus, nowDate);
        if (updateNum <= 0 && updateFinishNum <= 0) {
            ajaxResult.put(id + "updateStatus", false);
        }
        ajaxResult.put("id", id);
        ajaxResult.put("updateStatus", false);
        return AjaxResult.success(ajaxResult);
    }
}


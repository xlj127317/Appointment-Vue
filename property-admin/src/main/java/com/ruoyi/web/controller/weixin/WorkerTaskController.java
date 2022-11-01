package com.ruoyi.web.controller.weixin;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.property.domain.WorkerTask;
import com.ruoyi.property.service.WorkerTaskService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用工任务Controller
 *
 * @author wind
 * @date 2022-10-28
 */
@Api(tags = "用工任务")
@RestController
@RequestMapping("/hcx/property/task")
public class WorkerTaskController extends BaseController {
    @Autowired
    private WorkerTaskService workerTaskService;

    /**
     * 查询用工任务列表
     */
    @ApiOperation("查询用工任务列表")
    @PreAuthorize("@ss.hasPermi('property:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkerTask workerTask) {
        startPage();
        List<WorkerTask> list = workerTaskService.selectWorkerTaskList(workerTask);
        return getDataTable(list);
    }

    /**
     * 导出用工任务列表
     */
    @ApiOperation("导出用工任务列表")
    @PreAuthorize("@ss.hasPermi('property:task:export')")
    @Log(title = "用工任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WorkerTask workerTask) {
        List<WorkerTask> list = workerTaskService.selectWorkerTaskList(workerTask);
        ExcelUtil<WorkerTask> util = new ExcelUtil<>(WorkerTask.class);
        util.exportExcel(response, list, "用工任务数据");
    }

    /**
     * 获取用工任务详细信息
     */
    @ApiOperation("获取用工任务详细信息")
    @PreAuthorize("@ss.hasPermi('property:task:query')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam String id) {
        return AjaxResult.success(workerTaskService.selectWorkerTaskById(id));
    }

    /**
     * 新增用工任务
     */
    @ApiOperation("新增用工任务")
    @PreAuthorize("@ss.hasPermi('property:task:add')")
    @Log(title = "用工任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkerTask workerTask) {
        return toAjax(workerTaskService.insertWorkerTask(workerTask));
    }

    /**
     * 修改用工任务
     */
    @ApiOperation("修改用工任务")
    @PreAuthorize("@ss.hasPermi('property:task:edit')")
    @Log(title = "用工任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkerTask workerTask) {
        return toAjax(workerTaskService.updateWorkerTask(workerTask));
    }

    /**
     * 删除用工任务
     */
    @ApiOperation("删除用工任务")
    @PreAuthorize("@ss.hasPermi('property:task:remove')")
    @Log(title = "用工任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workerTaskService.deleteWorkerTaskByIds(ids));
    }

    @ApiOperation("修改任务完成情况")
    @PreAuthorize("@ss.hasAnyPermi('property:task:edit')")
    @Log(title = "用工任务完成", businessType = BusinessType.UPDATE)
    @GetMapping("/updateStatusById")
    public AjaxResult updateStatusById(@RequestParam String id,
                                       @RequestParam Integer completeStatus) {
        return toAjax(workerTaskService.updateStatusById(id, completeStatus));
    }
}

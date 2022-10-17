package com.ruoyi.web.controller.web;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.property.domain.Worker;
import com.ruoyi.property.service.WorkerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用工管理Controller
 *
 * @author wind
 * @date 2022-10-11
 */
@Api(tags = "用工管理")
@RestController
@RequestMapping("/hcx/property/worker")
public class WorkerController extends BaseController {
    @Autowired
    private WorkerService workerService;

    /**
     * 查询用工管理列表
     */
    @ApiOperation("查询用工管理列表")
    @PreAuthorize("@ss.hasPermi('property:worker:list')")
    @GetMapping("/list")
    public TableDataInfo list(Worker worker) {
        startPage();
        List<Worker> list = workerService.selectWorkerList(worker);
        return getDataTable(list);
    }

    /**
     * 导出用工管理列表
     */
    @ApiOperation("导出用工管理列表")
    @PreAuthorize("@ss.hasPermi('property:worker:export')")
    @Log(title = "用工管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Worker worker) {
        List<Worker> list = workerService.selectWorkerList(worker);
        ExcelUtil<Worker> util = new ExcelUtil<>(Worker.class);
        util.exportExcel(response, list, "用工管理数据");
    }

    /**
     * 获取用工管理详细信息
     */
    @ApiOperation("获取用工管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:worker:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(workerService.selectWorkerById(id));
    }

    /**
     * 新增用工管理
     */
    @ApiOperation("新增用工管理")
    @PreAuthorize("@ss.hasPermi('property:worker:add')")
    @Log(title = "用工管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Worker worker) {
        return toAjax(workerService.insertWorker(worker));
    }

    /**
     * 修改用工管理
     */
    @ApiOperation("修改用工管理")
    @PreAuthorize("@ss.hasPermi('property:worker:edit')")
    @Log(title = "用工管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Worker worker) {
        return toAjax(workerService.updateWorker(worker));
    }

    /**
     * 删除用工管理
     */
    @ApiOperation("删除用工管理")
    @PreAuthorize("@ss.hasPermi('property:worker:remove')")
    @Log(title = "用工管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(workerService.deleteWorkerByIds(ids));
    }
}

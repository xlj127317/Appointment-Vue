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
import com.ruoyi.property.domain.Report;
import com.ruoyi.property.service.IReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工单管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "工单管理")
@RestController
@RequestMapping("/hcx/property/report")
public class ReportController extends BaseController {
    @Autowired
    private IReportService reportService;

/**
 * 表查询工单管理列
 */
@ApiOperation("表查询工单管理列")
@PreAuthorize("@ss.hasPermi('property:report:list')")
@GetMapping("/list")
    public TableDataInfo list(Report report) {
        startPage();
        List<Report> list = reportService.selectReportList(report);
        return getDataTable(list);
    }

    /**
     * 导出工单管理列表
     */
    @ApiOperation("导出工单管理列表")
    @PreAuthorize("@ss.hasPermi('property:report:export')")
    @Log(title = "工单管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Report report) {
        List<Report> list = reportService.selectReportList(report);
        ExcelUtil<Report> util = new ExcelUtil<Report>(Report. class);
        util.exportExcel(response, list, "工单管理数据");
    }

    /**
     * 获取工单管理详细信息
     */
    @ApiOperation("获取工单管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:report:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(reportService.selectReportById(id));
    }

    /**
     * 新增工单管理
     */
    @ApiOperation("新增工单管理")
    @PreAuthorize("@ss.hasPermi('property:report:add')")
    @Log(title = "工单管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Report report) {
        return toAjax(reportService.insertReport(report));
    }

    /**
     * 修改工单管理
     */
    @ApiOperation("修改工单管理")
    @PreAuthorize("@ss.hasPermi('property:report:edit')")
    @Log(title = "工单管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Report report) {
        return toAjax(reportService.updateReport(report));
    }

    /**
     * 删除工单管理
     */
    @ApiOperation("删除工单管理")
    @PreAuthorize("@ss.hasPermi('property:report:remove')")
    @Log(title = "工单管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(reportService.deleteReportByIds(ids));
    }
}

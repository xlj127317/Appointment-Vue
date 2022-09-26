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
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.service.IAdvanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 预收管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "预收管理")
@RestController
@RequestMapping("/hcx/property/advance")
public class AdvanceController extends BaseController {
    @Autowired
    private IAdvanceService advanceService;

/**
 * 表查询预收管理列
 */
@ApiOperation("表查询预收管理列")
@PreAuthorize("@ss.hasPermi('property:advance:list')")
@GetMapping("/list")
    public TableDataInfo list(Advance advance) {
        startPage();
        List<Advance> list = advanceService.selectAdvanceList(advance);
        return getDataTable(list);
    }

    /**
     * 导出预收管理列表
     */
    @ApiOperation("导出预收管理列表")
    @PreAuthorize("@ss.hasPermi('property:advance:export')")
    @Log(title = "预收管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Advance advance) {
        List<Advance> list = advanceService.selectAdvanceList(advance);
        ExcelUtil<Advance> util = new ExcelUtil<Advance>(Advance. class);
        util.exportExcel(response, list, "预收管理数据");
    }

    /**
     * 获取预收管理详细信息
     */
    @ApiOperation("获取预收管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:advance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(advanceService.selectAdvanceById(id));
    }

    /**
     * 新增预收管理
     */
    @ApiOperation("新增预收管理")
    @PreAuthorize("@ss.hasPermi('property:advance:add')")
    @Log(title = "预收管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Advance advance) {
        return toAjax(advanceService.insertAdvance(advance));
    }

    /**
     * 修改预收管理
     */
    @ApiOperation("修改预收管理")
    @PreAuthorize("@ss.hasPermi('property:advance:edit')")
    @Log(title = "预收管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Advance advance) {
        return toAjax(advanceService.updateAdvance(advance));
    }

    /**
     * 删除预收管理
     */
    @ApiOperation("删除预收管理")
    @PreAuthorize("@ss.hasPermi('property:advance:remove')")
    @Log(title = "预收管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(advanceService.deleteAdvanceByIds(ids));
    }
}

package com.ruoyi.web.controller.web;

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
import com.ruoyi.property.domain.Owner;
import com.ruoyi.property.service.IOwnerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业主管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "业主管理")
@RestController
@RequestMapping("/hcx/property/owner")
public class OwnerController extends BaseController {
    @Autowired
    private IOwnerService ownerService;

    /**
     * 表查询业主管理列
     */
    @ApiOperation("表查询业主管理列")
    @PreAuthorize("@ss.hasPermi('property:owner:list')")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Owner owner) {
        startPage();
        List<Owner> list = ownerService.selectOwnerList(owner);
        return getDataTable(list);
    }

    /**
     * 导出业主管理列表
     */
    @ApiOperation("导出业主管理列表")
    @PreAuthorize("@ss.hasPermi('property:owner:export')")
    @Log(title = "业主管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Owner owner) {
        List<Owner> list = ownerService.selectOwnerList(owner);
        ExcelUtil<Owner> util = new ExcelUtil<>(Owner.class);
        util.exportExcel(response, list, "业主管理数据");
    }

    /**
     * 获取业主管理详细信息
     */
    @ApiOperation("获取业主管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:owner:query')")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(ownerService.selectOwnerById(id));
    }

    /**
     * 新增业主管理
     */
    @ApiOperation("新增业主管理")
    @PreAuthorize("@ss.hasPermi('property:owner:add')")
    @Log(title = "业主管理", businessType = BusinessType.INSERT)
    @PostMapping("/insert")
    public AjaxResult add(@RequestBody Owner owner) {
        return toAjax(ownerService.insertOwner(owner));
    }

    /**
     * 修改业主管理
     */
    @ApiOperation("修改业主管理")
    @PreAuthorize("@ss.hasPermi('property:owner:edit')")
    @Log(title = "业主管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Owner owner) {
        return toAjax(ownerService.updateOwner(owner));
    }

    /**
     * 删除业主管理
     */
    @ApiOperation("删除业主管理")
    @PreAuthorize("@ss.hasPermi('property:owner:remove')")
    @Log(title = "业主管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(ownerService.deleteOwnerByIds(ids));
    }

    @ApiOperation("按照月份统计企业入驻量")
    @GetMapping("/getSettled")
    public AjaxResult getSettled() {
        return ownerService.getSettled();
    }

}

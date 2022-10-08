package com.ruoyi.web.controller.weixin;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.service.FurnishService;
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
import com.ruoyi.property.domain.Furnish;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 装修办理申请Controller
 *
 * @author wind
 * @date 2022-09-29
 */
@Api(tags = "装修办理申请")
@RestController
@RequestMapping("/hcx/property/furnish")
public class FurnishController extends BaseController {
    @Autowired
    private FurnishService furnishService;

    /**
     * 查询装修办理申请列表
     */
    @ApiOperation("查询装修办理申请列表")
    @PreAuthorize("@ss.hasPermi('property:furnish:list')")
    @GetMapping("/list")
    public TableDataInfo list(Furnish furnish) {
        startPage();
        List<Furnish> list = furnishService.selectFurnishList(furnish);
        return getDataTable(list);
    }

    /**
     * 导出装修办理申请列表
     */
    @ApiOperation("导出装修办理申请列表")
    @PreAuthorize("@ss.hasPermi('property:furnish:export')")
    @Log(title = "装修办理申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Furnish furnish) {
        List<Furnish> list = furnishService.selectFurnishList(furnish);
        ExcelUtil<Furnish> util = new ExcelUtil<>(Furnish.class);
        util.exportExcel(response, list, "装修办理申请数据");
    }

    /**
     * 获取装修办理申请详细信息
     */
    @ApiOperation("获取装修办理申请详细信息")
    @PreAuthorize("@ss.hasPermi('property:furnish:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(furnishService.selectFurnishById(id));
    }

    /**
     * 新增装修办理申请
     */
    @ApiOperation("新增装修办理申请")
    @PreAuthorize("@ss.hasPermi('property:furnish:add')")
    @Log(title = "装修办理申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Furnish furnish) {
        return toAjax(furnishService.insertFurnish(furnish));
    }

    /**
     * 修改装修办理申请
     */
    @ApiOperation("修改装修办理申请")
    @PreAuthorize("@ss.hasPermi('property:furnish:edit')")
    @Log(title = "装修办理申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Furnish furnish) {
        return toAjax(furnishService.updateFurnish(furnish));
    }

    /**
     * 删除装修办理申请
     */
    @ApiOperation("删除装修办理申请")
    @PreAuthorize("@ss.hasPermi('property:furnish:remove')")
    @Log(title = "装修办理申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(furnishService.deleteFurnishByIds(ids));
    }
}

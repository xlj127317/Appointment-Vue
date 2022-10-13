package com.ruoyi.web.controller.web;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.service.CruiseService;
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
import com.ruoyi.property.domain.Cruise;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 巡航管理Controller
 *
 * @author wind
 * @date 2022-09-24
 */
@Api(tags = "巡航管理")
@RestController
@RequestMapping("/hcx/property/cruise")
public class CruiseController extends BaseController {
    @Autowired
    private CruiseService cruiseService;

    /**
     * 查询巡航管理列表
     */
    @ApiOperation("查询巡航管理列表")
    @PreAuthorize("@ss.hasPermi('property:cruise:list')")
    @GetMapping("/list")
    public TableDataInfo list(Cruise cruise) {
        startPage();
        List<Cruise> list = cruiseService.selectCruiseList(cruise);
        return getDataTable(list);
    }

    /**
     * 导出巡航管理列表
     */
    @ApiOperation("导出巡航管理列表")
    @PreAuthorize("@ss.hasPermi('property:cruise:export')")
    @Log(title = "巡航管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Cruise cruise) {
        List<Cruise> list = cruiseService.selectCruiseList(cruise);
        ExcelUtil<Cruise> util = new ExcelUtil<Cruise>(Cruise.class);
        util.exportExcel(response, list, "巡航管理数据");
    }

    /**
     * 获取巡航管理详细信息
     */
    @ApiOperation("获取巡航管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:cruise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(cruiseService.selectCruiseById(id));
    }

    /**
     * 新增巡航管理
     */
    @ApiOperation("新增巡航管理")
    @PreAuthorize("@ss.hasPermi('property:cruise:add')")
    @Log(title = "巡航管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Cruise cruise) {
        return toAjax(cruiseService.insertCruise(cruise));
    }

    /**
     * 修改巡航管理
     */
    @ApiOperation("修改巡航管理")
    @PreAuthorize("@ss.hasPermi('property:cruise:edit')")
    @Log(title = "巡航管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Cruise cruise) {
        return toAjax(cruiseService.updateCruise(cruise));
    }

    /**
     * 删除巡航管理
     */
    @ApiOperation("删除巡航管理")
    @PreAuthorize("@ss.hasPermi('property:cruise:remove')")
    @Log(title = "巡航管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(cruiseService.deleteCruiseByIds(ids));
    }
}

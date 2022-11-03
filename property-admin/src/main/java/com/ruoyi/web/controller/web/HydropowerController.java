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
import com.ruoyi.property.domain.Hydropower;
import com.ruoyi.property.service.HydropowerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 水电统计Controller
 *
 * @author wind
 * @date 2022-11-03
 */
@Api(tags = "水电统计")
@RestController
@RequestMapping("/hcx/property/hydropower")
public class HydropowerController extends BaseController {
    @Autowired
    private HydropowerService hydropowerService;

    /**
     * 查询水电统计列表
     */
    @ApiOperation("查询水电统计列表")
    @PreAuthorize("@ss.hasPermi('property:hydropower:list')")
    @GetMapping("/list")
    public TableDataInfo list(Hydropower hydropower) {
        startPage();
        List<Hydropower> list = hydropowerService.selectHydropowerList(hydropower);
        return getDataTable(list);
    }

    /**
     * 导出水电统计列表
     */
    @ApiOperation("导出水电统计列表")
    @PreAuthorize("@ss.hasPermi('property:hydropower:export')")
    @Log(title = "水电统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Hydropower hydropower) {
        List<Hydropower> list = hydropowerService.selectHydropowerList(hydropower);
        ExcelUtil<Hydropower> util = new ExcelUtil<>(Hydropower.class);
        util.exportExcel(response, list, "水电统计数据");
    }

    /**
     * 获取水电统计详细信息
     */
    @ApiOperation("获取水电统计详细信息")
    @PreAuthorize("@ss.hasPermi('property:hydropower:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(hydropowerService.selectHydropowerById(id));
    }

    /**
     * 新增水电统计
     */
    @ApiOperation("新增水电统计")
    @PreAuthorize("@ss.hasPermi('property:hydropower:add')")
    @Log(title = "水电统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Hydropower hydropower) {
        return toAjax(hydropowerService.insertHydropower(hydropower));
    }

    /**
     * 修改水电统计
     */
    @ApiOperation("修改水电统计")
    @PreAuthorize("@ss.hasPermi('property:hydropower:edit')")
    @Log(title = "水电统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Hydropower hydropower) {
        return toAjax(hydropowerService.updateHydropower(hydropower));
    }

    /**
     * 删除水电统计
     */
    @ApiOperation("删除水电统计")
    @PreAuthorize("@ss.hasPermi('property:hydropower:remove')")
    @Log(title = "水电统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(hydropowerService.deleteHydropowerByIds(ids));
    }
}

package com.ruoyi.web.controller.weixin;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.service.ThingOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.property.domain.ThingOut;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物品出入申请Controller
 *
 * @author wind
 * @date 2022-09-29
 */
@Api(tags = "物品出入申请")
@RestController
@RequestMapping("/hcx/property/out")
public class ThingOutController extends BaseController {
    @Autowired
    private ThingOutService thingOutService;

    /**
     * 查询物品出入申请列表
     */
    @ApiOperation("查询物品出入申请列表")
    @PreAuthorize("@ss.hasPermi('property:out:list')")
    @GetMapping("/list")
    public TableDataInfo list(ThingOut thingOut) {
        startPage();
        List<ThingOut> list = thingOutService.selectThingOutList(thingOut);
        return getDataTable(list);
    }

    /**
     * 导出物品出入申请列表
     */
    @ApiOperation("导出物品出入申请列表")
    @PreAuthorize("@ss.hasPermi('property:out:export')")
    @Log(title = "物品出入申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ThingOut thingOut) {
        List<ThingOut> list = thingOutService.selectThingOutList(thingOut);
        ExcelUtil<ThingOut> util = new ExcelUtil<>(ThingOut.class);
        util.exportExcel(response, list, "物品出入申请数据");
    }

    /**
     * 获取物品出入申请详细信息
     */
    @ApiOperation("获取物品出入申请详细信息")
    @PreAuthorize("@ss.hasPermi('property:out:query')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam String id) {
        return AjaxResult.success(thingOutService.selectThingOutById(id));
    }

    /**
     * 新增物品出入申请
     */
    @ApiOperation("新增物品出入申请")
    @PreAuthorize("@ss.hasPermi('property:out:add')")
    @Log(title = "物品出入申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ThingOut thingOut) {
        return toAjax(thingOutService.insertThingOut(thingOut));
    }

    /**
     * 修改物品出入申请
     */
    @ApiOperation("修改物品出入申请")
    @PreAuthorize("@ss.hasPermi('property:out:edit')")
    @Log(title = "物品出入申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ThingOut thingOut) {
        return toAjax(thingOutService.updateThingOut(thingOut));
    }

    /**
     * 删除物品出入申请
     */
    @ApiOperation("删除物品出入申请")
    @PreAuthorize("@ss.hasPermi('property:out:remove')")
    @Log(title = "物品出入申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(thingOutService.deleteThingOutByIds(ids));
    }
}

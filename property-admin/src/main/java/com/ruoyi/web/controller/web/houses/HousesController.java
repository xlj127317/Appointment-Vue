package com.ruoyi.web.controller.web.houses;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.property.domain.Houses;
import com.ruoyi.property.service.IHousesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/15 14:28
 **/
@RestController
@RequestMapping("/hcx/property/houses")
@Api(tags = "房屋管理")
public class HousesController extends BaseController {
    @Autowired
    private IHousesService housesService;

    /**
     * 表查询房屋管理列表
     */
    @ApiOperation("表查询房屋管理列表")
    @PreAuthorize("@ss.hasPermi('property:houses:list')")
    @GetMapping("/list")
    public TableDataInfo list(Houses houses) {
        startPage();
        List<Houses> list = housesService.selectHousesList(houses);
        return getDataTable(list);
    }

    /**
     * 导出房屋管理列表
     */
    @ApiOperation("导出房屋管理列表")
    @PreAuthorize("@ss.hasPermi('property:houses:export')")
    @Log(title = "房屋管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Houses houses) {
        List<Houses> list = housesService.selectHousesList(houses);
        ExcelUtil<Houses> util = new ExcelUtil<>(Houses.class);
        util.exportExcel(response, list, "房屋管理数据");
    }

    /**
     * 获取房屋管理详细信息
     */
    @ApiOperation("获取房屋管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:houses:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(housesService.selectHousesById(id));
    }

    /**
     * 新增房屋管理
     */
    @ApiOperation("新增房屋管理")
    @PreAuthorize("@ss.hasPermi('property:houses:add')")
    @Log(title = "房屋管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Houses houses) {
        return toAjax(housesService.insertHouses(houses));
    }

    /**
     * 修改房屋管理
     */
    @ApiOperation("修改房屋管理")
    @PreAuthorize("@ss.hasPermi('property:houses:edit')")
    @Log(title = "房屋管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Houses houses) {
        return toAjax(housesService.updateHouses(houses));
    }

    /**
     * 删除房屋管理
     */
    @ApiOperation("删除房屋管理")
    @PreAuthorize("@ss.hasPermi('property:houses:remove')")
    @Log(title = "房屋管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(housesService.deleteHousesByIds(ids));
    }
}

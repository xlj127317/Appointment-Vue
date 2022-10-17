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
import com.ruoyi.property.domain.RentSell;
import com.ruoyi.property.service.IRentSellService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 租售管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "租售管理")
@RestController
@RequestMapping("/hcx/property/sell")
public class RentSellController extends BaseController {
    @Autowired
    private IRentSellService rentSellService;

    /**
     * 表查询租售管理列
     */
    @ApiOperation("表查询租售管理列")
    @PreAuthorize("@ss.hasPermi('property:sell:list')")
    @GetMapping("/list")
    public TableDataInfo list(RentSell rentSell) {
        startPage();
        List<RentSell> list = rentSellService.selectRentSellList(rentSell);
        return getDataTable(list);
    }

    /**
     * 导出租售管理列表
     */
    @ApiOperation("导出租售管理列表")
    @PreAuthorize("@ss.hasPermi('property:sell:export')")
    @Log(title = "租售管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RentSell rentSell) {
        List<RentSell> list = rentSellService.selectRentSellList(rentSell);
        ExcelUtil<RentSell> util = new ExcelUtil<>(RentSell.class);
        util.exportExcel(response, list, "租售管理数据");
    }

    /**
     * 获取租售管理详细信息
     */
    @ApiOperation("获取租售管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:sell:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(rentSellService.selectRentSellById(id));
    }

    /**
     * 新增租售管理
     */
    @ApiOperation("新增租售管理")
    @PreAuthorize("@ss.hasPermi('property:sell:add')")
    @Log(title = "租售管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RentSell rentSell) {
        return toAjax(rentSellService.insertRentSell(rentSell));
    }

    /**
     * 修改租售管理
     */
    @ApiOperation("修改租售管理")
    @PreAuthorize("@ss.hasPermi('property:sell:edit')")
    @Log(title = "租售管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RentSell rentSell) {
        return toAjax(rentSellService.updateRentSell(rentSell));
    }

    /**
     * 删除租售管理
     */
    @ApiOperation("删除租售管理")
    @PreAuthorize("@ss.hasPermi('property:sell:remove')")
    @Log(title = "租售管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(rentSellService.deleteRentSellByIds(ids));
    }
}

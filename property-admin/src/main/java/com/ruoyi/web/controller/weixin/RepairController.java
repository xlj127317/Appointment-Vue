package com.ruoyi.web.controller.weixin;

import java.time.Year;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.service.RepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.property.domain.Repair;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物业报修申请Controller
 *
 * @author wind
 * @date 2022-09-29
 */
@Api(tags = "物业报修申请")
@RestController
@RequestMapping("/hcx/property/repair")
public class RepairController extends BaseController {
    @Autowired
    private RepairService repairService;

    /**
     * 查询物业报修申请列表
     */
    @ApiOperation("查询物业报修申请列表")
    @PreAuthorize("@ss.hasPermi('property:repair:list')")
    @GetMapping("/list")
    public TableDataInfo list(Repair repair) {
        startPage();
        List<Repair> list = repairService.selectRepairList(repair);
        return getDataTable(list);
    }

    /**
     * 导出物业报修申请列表
     */
    @ApiOperation("导出物业报修申请列表")
    @PreAuthorize("@ss.hasPermi('property:repair:export')")
    @Log(title = "物业报修申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Repair repair) {
        List<Repair> list = repairService.selectRepairList(repair);
        ExcelUtil<Repair> util = new ExcelUtil<>(Repair.class);
        util.exportExcel(response, list, "物业报修申请数据");
    }

    /**
     * 获取物业报修申请详细信息
     */
    @ApiOperation("获取物业报修申请详细信息")
    @PreAuthorize("@ss.hasPermi('property:repair:query')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam String id) {
        return AjaxResult.success(repairService.selectRepairById(id));
    }

    /**
     * 新增物业报修申请
     */
    @ApiOperation("新增物业报修申请")
    @PreAuthorize("@ss.hasPermi('property:repair:add')")
    @Log(title = "物业报修申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Repair repair) {
        return toAjax(repairService.insertRepair(repair));
    }

    /**
     * 修改物业报修申请
     */
    @ApiOperation("修改物业报修申请")
    @PreAuthorize("@ss.hasPermi('property:repair:edit')")
    @Log(title = "物业报修申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Repair repair) {
        return toAjax(repairService.updateRepair(repair));
    }

    /**
     * 删除物业报修申请
     */
    @ApiOperation("删除物业报修申请")
    @PreAuthorize("@ss.hasPermi('property:repair:remove')")
    @Log(title = "物业报修申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(repairService.deleteRepairByIds(ids));
    }

    @GetMapping("/chart")
    public AjaxResult chart() {
        return AjaxResult.success(repairService.getAmountValueChart(Year.now().getValue()));
    }
}

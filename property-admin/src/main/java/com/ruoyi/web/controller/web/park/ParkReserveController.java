package com.ruoyi.web.controller.web.park;

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
import com.ruoyi.property.domain.ParkReserve;
import com.ruoyi.property.service.ParkReserveService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 园区资源预约Controller
 *
 * @author wind
 * @date 2022-10-18
 */
@Api(tags = "园区资源预约")
@RestController
@RequestMapping("/hcx/property/reserve")
public class ParkReserveController extends BaseController {
    @Autowired
    private ParkReserveService parkReserveService;

    /**
     * 查询园区资源预约列表
     */
    @ApiOperation("查询园区资源预约列表")
    @PreAuthorize("@ss.hasPermi('property:reserve:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkReserve parkReserve) {
        startPage();
        List<ParkReserve> list = parkReserveService.selectParkReserveList(parkReserve);
        return getDataTable(list);
    }

    /**
     * 获取园区资源预约详细信息
     */
    @ApiOperation("获取园区资源预约详细信息")
    @PreAuthorize("@ss.hasPermi('property:reserve:query')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam String id) {
        return AjaxResult.success(parkReserveService.selectParkReserveById(id));
    }

    /**
     * 新增园区资源预约
     */
    @ApiOperation("新增园区资源预约")
    @PreAuthorize("@ss.hasPermi('property:reserve:add')")
    @Log(title = "园区资源预约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkReserve parkReserve) {
        return toAjax(parkReserveService.insertParkReserve(parkReserve));
    }

    /**
     * 修改园区资源预约
     */
    @ApiOperation("修改园区资源预约")
    @PreAuthorize("@ss.hasPermi('property:reserve:edit')")
    @Log(title = "园区资源预约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkReserve parkReserve) {
        return toAjax(parkReserveService.updateParkReserve(parkReserve));
    }

    /**
     * 删除园区资源预约
     */
    @ApiOperation("删除园区资源预约")
    @PreAuthorize("@ss.hasPermi('property:reserve:remove')")
    @Log(title = "园区资源预约", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(parkReserveService.deleteParkReserveByIds(ids));
    }
}

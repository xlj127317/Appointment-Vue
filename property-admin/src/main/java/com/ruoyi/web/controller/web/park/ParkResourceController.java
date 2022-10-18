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
import com.ruoyi.property.domain.ParkResource;
import com.ruoyi.property.service.ParkResourceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 园区资源Controller
 *
 * @author wind
 * @date 2022-10-18
 */
@Api(tags = "园区资源")
@RestController
@RequestMapping("/hcx/property/resource")
public class ParkResourceController extends BaseController {
    @Autowired
    private ParkResourceService parkResourceService;

    /**
     * 查询园区资源列表
     */
    @ApiOperation("查询园区资源列表")
    @PreAuthorize("@ss.hasPermi('property:resource:list')")
    @GetMapping("/list")
    public TableDataInfo list(ParkResource parkResource) {
        startPage();
        List<ParkResource> list = parkResourceService.selectParkResourceList(parkResource);
        return getDataTable(list);
    }

    /**
     * 获取园区资源详细信息
     */
    @ApiOperation("获取园区资源详细信息")
    @PreAuthorize("@ss.hasPermi('property:resource:query')")
    @GetMapping(value = "/getInfo")
    public AjaxResult getInfo(@RequestParam String id) {
        return AjaxResult.success(parkResourceService.selectParkResourceById(id));
    }

    /**
     * 新增园区资源
     */
    @ApiOperation("新增园区资源")
    @PreAuthorize("@ss.hasPermi('property:resource:add')")
    @Log(title = "园区资源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ParkResource parkResource) {
        return toAjax(parkResourceService.insertParkResource(parkResource));
    }

    /**
     * 修改园区资源
     */
    @ApiOperation("修改园区资源")
    @PreAuthorize("@ss.hasPermi('property:resource:edit')")
    @Log(title = "园区资源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ParkResource parkResource) {
        return toAjax(parkResourceService.updateParkResource(parkResource));
    }

    /**
     * 删除园区资源
     */
    @ApiOperation("删除园区资源")
    @PreAuthorize("@ss.hasPermi('property:resource:remove')")
    @Log(title = "园区资源", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(parkResourceService.deleteParkResourceByIds(ids));
    }
}

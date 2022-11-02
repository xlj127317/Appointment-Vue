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
import com.ruoyi.property.domain.FirmInfo;
import com.ruoyi.property.service.FirmInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 企业信息Controller
 *
 * @author wind
 * @date 2022-11-02
 */
@Api(tags = "企业信息")
@RestController
@RequestMapping("/hcx/property/firm")
public class FirmInfoController extends BaseController {
    @Autowired
    private FirmInfoService firmInfoService;

/**
 * 查询企业信息列表
 */
@ApiOperation("查询企业信息列表")
@PreAuthorize("@ss.hasPermi('property:firm:list')")
@GetMapping("/list")
    public TableDataInfo list(FirmInfo firmInfo) {
        startPage();
        List<FirmInfo> list = firmInfoService.selectFirmInfoList(firmInfo);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @ApiOperation("导出企业信息列表")
    @PreAuthorize("@ss.hasPermi('property:firm:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FirmInfo firmInfo) {
        List<FirmInfo> list = firmInfoService.selectFirmInfoList(firmInfo);
        ExcelUtil<FirmInfo> util = new ExcelUtil<>(FirmInfo. class);
        util.exportExcel(response, list, "企业信息数据");
    }

    /**
     * 获取企业信息详细信息
     */
    @ApiOperation("获取企业信息详细信息")
    @PreAuthorize("@ss.hasPermi('property:firm:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(firmInfoService.selectFirmInfoById(id));
    }

    /**
     * 新增企业信息
     */
    @ApiOperation("新增企业信息")
    @PreAuthorize("@ss.hasPermi('property:firm:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FirmInfo firmInfo) {
        return toAjax(firmInfoService.insertFirmInfo(firmInfo));
    }

    /**
     * 修改企业信息
     */
    @ApiOperation("修改企业信息")
    @PreAuthorize("@ss.hasPermi('property:firm:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FirmInfo firmInfo) {
        return toAjax(firmInfoService.updateFirmInfo(firmInfo));
    }

    /**
     * 删除企业信息
     */
    @ApiOperation("删除企业信息")
    @PreAuthorize("@ss.hasPermi('property:firm:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(firmInfoService.deleteFirmInfoByIds(ids));
    }
}

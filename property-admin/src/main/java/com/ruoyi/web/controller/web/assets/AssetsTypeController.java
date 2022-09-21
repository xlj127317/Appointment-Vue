package com.ruoyi.web.controller.web.assets;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.property.domain.AssetsType;
import com.ruoyi.property.service.IAssetsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/20 17:25
 **/
@Api(tags = "资产类别")
@RestController
@RequestMapping("/hcx/property/assetsType")
public class AssetsTypeController extends BaseController {
    @Autowired
    private IAssetsTypeService assetsTypeService;

    /**
     * 查询资产类别管理列表
     */
    @ApiOperation("查询资产类别管理列表")
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(AssetsType assetsType) {
        startPage();
        List<AssetsType> list = assetsTypeService.selectAssetsTypeList(assetsType);
        return getDataTable(list);
    }

    /**
     * 导出资产类别管理列表
     */
    @ApiOperation("导出资产类别管理列表")
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "资产类别管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AssetsType assetsType) {
        List<AssetsType> list = assetsTypeService.selectAssetsTypeList(assetsType);
        ExcelUtil<AssetsType> util = new ExcelUtil<AssetsType>(AssetsType.class);
        util.exportExcel(response, list, "资产类别管理数据");
    }

    /**
     * 获取资产类别管理详细信息
     */
    @ApiOperation("获取资产类别管理详细信息")
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(assetsTypeService.selectAssetsTypeById(id));
    }

    /**
     * 新增资产类别管理
     */
    @ApiOperation("新增资产类别管理")
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "资产类别管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AssetsType assetsType) {
        return toAjax(assetsTypeService.insertAssetsType(assetsType));
    }

    /**
     * 修改资产类别管理
     */
    @ApiOperation("修改资产类别管理")
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "资产类别管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AssetsType assetsType) {
        return toAjax(assetsTypeService.updateAssetsType(assetsType));
    }

    /**
     * 删除资产类别管理
     */
    @ApiOperation("删除资产类别管理")
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "资产类别管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(assetsTypeService.deleteAssetsTypeByIds(ids));
    }
}

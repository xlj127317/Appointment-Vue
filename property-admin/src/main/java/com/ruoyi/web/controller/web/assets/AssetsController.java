package com.ruoyi.web.controller.web.assets;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.property.domain.Assets;
import com.ruoyi.property.service.AssetsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 11:26
 **/
@RestController
@RequestMapping("/hcx/property/assets")
@Api(tags = "资产管理")
public class AssetsController extends BaseController {
    @Resource
    private AssetsService assetsService;

    @PreAuthorize("@ss.hasPermi('property:assets:add')")
    @Log(title = "资产管理", businessType = BusinessType.INSERT)
    @ApiOperation(value = "新增资产单")
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody Assets assets) {
        return assetsService.insertAssets(assets);
    }

    @PreAuthorize("@ss.hasPermi('property:assets:query')")
    @ApiOperation(value = "详情")
    @GetMapping("/selectById/{id}")
    public AjaxResult queryById(@PathVariable String id) {
        return assetsService.queryById(id);
    }

    @PreAuthorize("@ss.hasPermi('property:assets:edit')")
    @Log(title = "资产管理", businessType = BusinessType.UPDATE)
    @ApiOperation(value = "修改资产单")
    @PostMapping("/updateById")
    public AjaxResult updateById(@RequestBody Assets assets) {
        return assetsService.updateById(assets);
    }

    @PreAuthorize("@ss.hasPermi('property:assets:remove')")
    @Log(title = "资产管理", businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除资产单")
    @DeleteMapping("/deleteByIds/{ids}")
    public AjaxResult deleteByIds(@PathVariable String... ids) {
        return toAjax(assetsService.deleteById(ids));
    }

    @ApiOperation(value = "分页查询资产单")
    @GetMapping("/queryByPage")
    @PreAuthorize("@ss.hasPermi('property:assets:list')")
    @ResponseBody
    public TableDataInfo queryPage(Assets assets) {
        startPage();
        List<Assets> list = assetsService.queryList(assets);
        return getDataTable(list);
    }
}

package com.ruoyi.web.controller.web.assets;

import com.ruoyi.property.domain.Assets;
import com.ruoyi.property.service.AssetsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "新增资产单")
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody Assets assets) {
        return assetsService.insertAssets(assets);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/selectById")
    public AjaxResult queryById(@RequestParam String id) {
        return assetsService.queryById(id);
    }

    @ApiOperation(value = "修改资产单")
    @PostMapping("/updateById")
    public AjaxResult updateById(@RequestBody Assets assets) {
        return assetsService.updateById(assets);
    }

    @ApiOperation(value = "删除资产单")
    @DeleteMapping("/deleteByIds/{ids}")
    public AjaxResult deleteByIds(@PathVariable String... ids) {
        return toAjax(assetsService.deleteById(ids));
    }

    @ApiOperation(value = "分页查询资产单")
    @GetMapping("/queryByPage")
    @ResponseBody
    public TableDataInfo queryPage(Assets assets) {
        startPage();
        List<Assets> list = assetsService.queryList(assets);
        return getDataTable(list);
    }
}

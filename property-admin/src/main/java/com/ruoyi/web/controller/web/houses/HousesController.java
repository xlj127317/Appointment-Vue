package com.ruoyi.web.controller.web.houses;

import com.ruoyi.property.service.HousesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.property.domain.Houses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 心风
 * @date 2022/09/15 14:28
 **/
@RestController
@RequestMapping("/hcx/property/houses")
@Api(tags = "房屋管理")
public class HousesController extends BaseController {
    @Resource
    private HousesService housesService;

    @ApiOperation(value = "新增房屋")
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody Houses houses) {
        return housesService.insertHouses(houses);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/selectById")
    public AjaxResult queryById(@RequestParam String id) {
        return housesService.queryById(id);
    }

    @ApiOperation(value = "修改房屋")
    @PostMapping("/updateById")
    public AjaxResult updateById(@RequestBody Houses houses) {
        return AjaxResult.success(housesService.updateById(houses));
    }

    @ApiOperation("房屋状态修改")
    @GetMapping("/updateStatus")
    public AjaxResult updateStatus(String id) {
        return housesService.updateStatus(id);
    }

    @ApiOperation(value = "删除房屋")
    @PostMapping("/deleteByIds")
    public AjaxResult deleteByIds(String... ids) {
        return housesService.deleteById(ids);
    }

    @ApiOperation(value = "分页查询房屋")
    @PostMapping("/queryByPage")
    @PreAuthorize("@ss.hasPermi('system:houses:list')")
    @ResponseBody
    public TableDataInfo queryPage(@RequestBody Houses houses) {
        startPage();
        List<Houses> list = housesService.queryList(houses);
        return getDataTable(list);
    }
}

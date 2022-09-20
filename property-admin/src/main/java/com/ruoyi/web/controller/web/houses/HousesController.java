package com.ruoyi.web.controller.web.houses;

import com.ruoyi.property.service.HousesService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.property.domain.Houses;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
        return housesService.updateHousesById(houses);
    }

    @ApiOperation(value = "删除房屋")
    @DeleteMapping("/deleteByIds/{ids}")
    public AjaxResult deleteByIds(@PathVariable String... ids) {
        return toAjax(housesService.deleteById(ids)) ;
    }

    @ApiOperation(value = "分页查询房屋")
    @GetMapping("/queryByPage")
    @PreAuthorize("@ss.hasPermi('system:houses:list')")
    public TableDataInfo queryPage(Houses houses) {
        startPage();
        List<Houses> list = housesService.queryList(houses);
        return getDataTable(list);
    }
}

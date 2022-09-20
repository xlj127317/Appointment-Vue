package com.ruoyi.web.controller.web.actually;

import com.ruoyi.property.domain.Actually;
import com.ruoyi.property.service.ActuallyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/09 15:20
 **/
@RestController
@RequestMapping("/hcx/property/actually")
@Api(tags = "应收管理")
public class ActuallyController extends BaseController {

    @Autowired
    private ActuallyService actuallyService;

    @ApiOperation(value = "新增应收单")
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody Actually actually) {
        return actuallyService.insertActually(actually);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/selectById")
    public AjaxResult queryById(@RequestParam String id) {
        return actuallyService.queryById(id);
    }

    @ApiOperation(value = "修改应收单")
    @PostMapping("/updateById")
    public AjaxResult updateById(@RequestBody Actually actually) {
        return actuallyService.updateById(actually);
    }

    @ApiOperation(value = "删除应收单")
    @PostMapping("/deleteByIds/{ids}")
    public AjaxResult deleteByIds(@PathVariable String... ids) {
        return toAjax(actuallyService.deleteById(ids));
    }

    @ApiOperation(value = "分页查询应收单")
    @GetMapping("/queryByPage")
    @ResponseBody
    public TableDataInfo queryPage(Actually actually) {
        startPage();
        List<Actually> list = actuallyService.queryList(actually);
        return getDataTable(list);
    }

}

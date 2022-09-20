package com.ruoyi.web.controller.web.actually;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.property.domain.ActuallyType;
import com.ruoyi.property.service.ActuallyTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 心风
 * @date 2022/09/16 17:28
 **/
@Api(tags = "应收类型")
@RestController
@RequestMapping("/hcx/property/actuallyType")
public class ActuallyTypeController extends BaseController {
    @Autowired
    private ActuallyTypeService actuallyTypeService;

    @ApiOperation(value = "新增应收类型")
    @PostMapping("/insert")
    public AjaxResult insert(@RequestBody ActuallyType actuallyType) {
        return actuallyTypeService.insertActuallyType(actuallyType);
    }

    @ApiOperation(value = "详情")
    @GetMapping("/selectById")
    public AjaxResult queryById(@RequestParam String id) {
        return actuallyTypeService.queryById(id);
    }

    @ApiOperation(value = "修改应收类型")
    @PostMapping("/updateById")
    public AjaxResult updateById(@RequestBody ActuallyType actuallyType) {
        return actuallyTypeService.updateById(actuallyType);
    }

    @ApiOperation(value = "删除应收类型")
    @DeleteMapping("/deleteByIds/{ids}")
    public AjaxResult deleteByIds(@PathVariable String... ids) {
        return toAjax(actuallyTypeService.deleteById(ids));
    }

    @ApiOperation(value = "分页查询应收类型")
    @GetMapping("/queryByPage")
    @ResponseBody
    public TableDataInfo queryPage(ActuallyType actuallyType) {
        startPage();
        List<ActuallyType> list = actuallyTypeService.queryList(actuallyType);
        return getDataTable(list);
    }
}

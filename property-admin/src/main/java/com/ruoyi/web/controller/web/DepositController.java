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
import com.ruoyi.property.domain.Deposit;
import com.ruoyi.property.service.IDepositService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 押金Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "押金")
@RestController
@RequestMapping("/hcx/property/deposit")
public class DepositController extends BaseController {
    @Autowired
    private IDepositService depositService;

/**
 * 表查询押金列
 */
@ApiOperation("表查询押金列")
@PreAuthorize("@ss.hasPermi('property:deposit:list')")
@GetMapping("/list")
    public TableDataInfo list(Deposit deposit) {
        startPage();
        List<Deposit> list = depositService.selectDepositList(deposit);
        return getDataTable(list);
    }

    /**
     * 导出押金列表
     */
    @ApiOperation("导出押金列表")
    @PreAuthorize("@ss.hasPermi('property:deposit:export')")
    @Log(title = "押金", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Deposit deposit) {
        List<Deposit> list = depositService.selectDepositList(deposit);
        ExcelUtil<Deposit> util = new ExcelUtil<Deposit>(Deposit. class);
        util.exportExcel(response, list, "押金数据");
    }

    /**
     * 获取押金详细信息
     */
    @ApiOperation("获取押金详细信息")
    @PreAuthorize("@ss.hasPermi('property:deposit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(depositService.selectDepositById(id));
    }

    /**
     * 新增押金
     */
    @ApiOperation("新增押金")
    @PreAuthorize("@ss.hasPermi('property:deposit:add')")
    @Log(title = "押金", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Deposit deposit) {
        return toAjax(depositService.insertDeposit(deposit));
    }

    /**
     * 修改押金
     */
    @ApiOperation("修改押金")
    @PreAuthorize("@ss.hasPermi('property:deposit:edit')")
    @Log(title = "押金", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Deposit deposit) {
        return toAjax(depositService.updateDeposit(deposit));
    }

    /**
     * 删除押金
     */
    @ApiOperation("删除押金")
    @PreAuthorize("@ss.hasPermi('property:deposit:remove')")
    @Log(title = "押金", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(depositService.deleteDepositByIds(ids));
    }
}

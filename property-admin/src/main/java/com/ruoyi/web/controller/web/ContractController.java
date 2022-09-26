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
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.service.IContractService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "合同管理")
@RestController
@RequestMapping("/hcx/property/contract")
public class ContractController extends BaseController {
    @Autowired
    private IContractService contractService;

/**
 * 表查询合同管理列
 */
@ApiOperation("表查询合同管理列")
@PreAuthorize("@ss.hasPermi('property:contract:list')")
@GetMapping("/list")
    public TableDataInfo list(Contract contract) {
        startPage();
        List<Contract> list = contractService.selectContractList(contract);
        return getDataTable(list);
    }

    /**
     * 导出合同管理列表
     */
    @ApiOperation("导出合同管理列表")
    @PreAuthorize("@ss.hasPermi('property:contract:export')")
    @Log(title = "合同管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Contract contract) {
        List<Contract> list = contractService.selectContractList(contract);
        ExcelUtil<Contract> util = new ExcelUtil<Contract>(Contract. class);
        util.exportExcel(response, list, "合同管理数据");
    }

    /**
     * 获取合同管理详细信息
     */
    @ApiOperation("获取合同管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:contract:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(contractService.selectContractById(id));
    }

    /**
     * 新增合同管理
     */
    @ApiOperation("新增合同管理")
    @PreAuthorize("@ss.hasPermi('property:contract:add')")
    @Log(title = "合同管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Contract contract) {
        return toAjax(contractService.insertContract(contract));
    }

    /**
     * 修改合同管理
     */
    @ApiOperation("修改合同管理")
    @PreAuthorize("@ss.hasPermi('property:contract:edit')")
    @Log(title = "合同管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Contract contract) {
        return toAjax(contractService.updateContract(contract));
    }

    /**
     * 删除合同管理
     */
    @ApiOperation("删除合同管理")
    @PreAuthorize("@ss.hasPermi('property:contract:remove')")
    @Log(title = "合同管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(contractService.deleteContractByIds(ids));
    }
}

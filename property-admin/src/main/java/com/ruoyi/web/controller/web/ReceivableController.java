package com.ruoyi.web.controller.web;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.service.IContractService;
import com.ruoyi.web.dto.ReceivableCreateInput;
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
import com.ruoyi.property.domain.Receivable;
import com.ruoyi.property.service.IReceivableService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 应收管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "应收管理")
@RestController
@RequestMapping("/hcx/property/receivable")
public class ReceivableController extends BaseController {
    @Autowired
    private IReceivableService receivableService;

    @Autowired
    private IContractService contractService;

/**
 * 表查询应收管理列
 */
@ApiOperation("表查询应收管理列")
@PreAuthorize("@ss.hasPermi('property:receivable:list')")
@GetMapping("/list")
    public TableDataInfo list(Receivable receivable) {
        startPage();
        List<Receivable> list = receivableService.selectReceivableList(receivable);
        return getDataTable(list);
    }

    /**
     * 导出应收管理列表
     */
    @ApiOperation("导出应收管理列表")
    @PreAuthorize("@ss.hasPermi('property:receivable:export')")
    @Log(title = "应收管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Receivable receivable) {
        List<Receivable> list = receivableService.selectReceivableList(receivable);
        ExcelUtil<Receivable> util = new ExcelUtil<Receivable>(Receivable. class);
        util.exportExcel(response, list, "应收管理数据");
    }

    /**
     * 获取应收管理详细信息
     */
    @ApiOperation("获取应收管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:receivable:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(receivableService.selectReceivableById(id));
    }

    /**
     * 新增应收管理
     */
    @ApiOperation("新增应收管理")
    // @PreAuthorize("@ss.hasPermi('property:receivable:add')")
    @Log(title = "应收管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReceivableCreateInput input) {
        Contract contract = contractService.selectContractById(input.getContractId());
        if (contract == null) {
            return error("合同不存在");
        }

        Receivable receivable = new Receivable();
        receivable.setContractId(contract.getId());
        receivable.setOwnerId(contract.getOwnerId());
        receivable.setPaymentId(input.getPaymentTypeId());
        receivable.setPaymentDate(input.getPaymentDate());
        receivable.setPaymentName(input.getPaymentName());
        receivable.setReceivableMoney(input.getReceivableMoney().toString());
        receivable.setPaymentContent(input.getPaymentContent());
        receivable.setStopTime(input.getExpiresDate());
        receivable.setPaymentType(0);
        receivable.setCreateId(getUserId());
        return toAjax(receivableService.insertReceivable(receivable));
    }

    /**
     * 修改应收管理
     */
    @ApiOperation("修改应收管理")
    @PreAuthorize("@ss.hasPermi('property:receivable:edit')")
    @Log(title = "应收管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Receivable receivable) {
        return toAjax(receivableService.updateReceivable(receivable));
    }

    /**
     * 删除应收管理
     */
    @ApiOperation("删除应收管理")
    @PreAuthorize("@ss.hasPermi('property:receivable:remove')")
    @Log(title = "应收管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(receivableService.deleteReceivableByIds(ids));
    }
}

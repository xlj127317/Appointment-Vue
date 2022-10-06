package com.ruoyi.web.controller.web;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.dto.AdvanceCreateInput;
import com.ruoyi.property.dto.AdvanceListInput;
import com.ruoyi.property.dto.AdvanceOutput;
import com.ruoyi.property.service.IAdvanceService;
import com.ruoyi.property.service.IContractService;
import com.ruoyi.property.service.IPaymentTypeService;
import com.ruoyi.property.service.IReceivableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 预收管理Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "预收管理")
@RestController
@RequestMapping("/hcx/property/advance")
public class AdvanceController extends BaseController {
    @Autowired
    private IAdvanceService advanceService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IPaymentTypeService paymentTypeService;

    @Autowired
    private IReceivableService receivableService;

    /**
     * 表查询预收管理列
     */
    @ApiOperation("表查询预收管理列")
    @PreAuthorize("@ss.hasPermi('property:advance:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody AdvanceListInput input) {
        startPage();
        ModelMapper modelMapper = new ModelMapper();
        List<Advance> list = advanceService.selectAdvanceList(input);
        List<AdvanceOutput> output = list.stream()
                .map(r -> modelMapper.map(r, AdvanceOutput.class))
                .collect(Collectors.toList());
        return getDataTable(output);
    }

    /**
     * 导出预收管理列表
     */
    @ApiOperation("导出预收管理列表")
    @PreAuthorize("@ss.hasPermi('property:advance:export')")
    @Log(title = "预收管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdvanceListInput input) {
        List<Advance> list = advanceService.selectAdvanceList(input);
        ExcelUtil<Advance> util = new ExcelUtil<Advance>(Advance. class);
        util.exportExcel(response, list, "预收管理数据");
    }

    /**
     * 获取预收管理详细信息
     */
    @ApiOperation("获取预收管理详细信息")
    @PreAuthorize("@ss.hasPermi('property:advance:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(advanceService.selectAdvanceById(id));
    }

    /**
     * 新增预收管理
     */
    @ApiOperation("新增预收管理")
    @PreAuthorize("@ss.hasPermi('property:advance:add')")
    @Log(title = "预收管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdvanceCreateInput input) {
        Contract contract = contractService.selectContractById(input.getContractId());
        if (contract == null) {
            return error("合同不存在");
        }

        PaymentType paymentType = paymentTypeService.selectPaymentTypeById(input.getPaymentType());
        if (paymentType == null) {
            return error("款项类别不存在");
        }

        if (advanceService.exists(input.getContractId(), paymentType.getId(), input.getAdvanceDate())) {
            return error("此款项该月份已有预收费用，无需重复缴纳");
        }

        if (receivableService.exists(input.getContractId(), paymentType.getId(), input.getAdvanceDate())) {
            return error("此款项已生成应收记录，请直接前往应收记录缴费");
        }

        ModelMapper modelMapper = new ModelMapper();
        Advance advance = modelMapper.map(input, Advance.class);
        advance.setDeductStatus(0);
        advance.setOwnerId(getUserId().toString());
        return toAjax(advanceService.insertAdvance(advance));
    }

    /**
     * 修改预收管理
     */
    @ApiOperation("修改预收管理")
    @PreAuthorize("@ss.hasPermi('property:advance:edit')")
    @Log(title = "预收管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Advance advance) {
        return toAjax(advanceService.updateAdvance(advance));
    }

    /**
     * 删除预收管理
     */
    @ApiOperation("删除预收管理")
    @PreAuthorize("@ss.hasPermi('property:advance:remove')")
    @Log(title = "预收管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(advanceService.deleteAdvanceByIds(ids));
    }
}

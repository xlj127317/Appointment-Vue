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
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.service.IPaymentTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 款项类型Controller
 *
 * @author wind
 * @date 2022-09-21
 */
@Api(tags = "款项类型")
@RestController
@RequestMapping("/hcx/property/type")
public class PaymentTypeController extends BaseController {
    @Autowired
    private IPaymentTypeService paymentTypeService;

/**
 * 表查询租售类型列
 */
@ApiOperation("查询款项类型列表")
@PreAuthorize("@ss.hasPermi('property:type:list')")
@GetMapping("/list")
    public TableDataInfo list(PaymentType paymentType) {
        startPage();
        List<PaymentType> list = paymentTypeService.selectPaymentTypeList(paymentType);
        return getDataTable(list);
    }

    /**
     * 导出租售类型列表
     */
    @ApiOperation("导出款项类型列表")
    @PreAuthorize("@ss.hasPermi('property:type:export')")
    @Log(title = "租售类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PaymentType paymentType) {
        List<PaymentType> list = paymentTypeService.selectPaymentTypeList(paymentType);
        ExcelUtil<PaymentType> util = new ExcelUtil<PaymentType>(PaymentType. class);
        util.exportExcel(response, list, "款项类型数据");
    }

    /**
     * 获取租售类型详细信息
     */
    @ApiOperation("获取款项类型详细信息")
    @PreAuthorize("@ss.hasPermi('property:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(paymentTypeService.selectPaymentTypeById(id));
    }

    /**
     * 新增租售类型
     */
    @ApiOperation("新增款项类型")
    @PreAuthorize("@ss.hasPermi('property:type:add')")
    @Log(title = "款项类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PaymentType paymentType) {
        return toAjax(paymentTypeService.insertPaymentType(paymentType));
    }

    /**
     * 修改租售类型
     */
    @ApiOperation("修改款项类型")
    @PreAuthorize("@ss.hasPermi('property:type:edit')")
    @Log(title = "款项类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PaymentType paymentType) {
        return toAjax(paymentTypeService.updatePaymentType(paymentType));
    }

    /**
     * 删除款项类型
     */
    @ApiOperation("删除款项类型")
    @PreAuthorize("@ss.hasPermi('property:type:remove')")
    @Log(title = "款项类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(paymentTypeService.deletePaymentTypeByIds(ids));
    }
}

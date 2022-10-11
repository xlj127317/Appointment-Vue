package com.ruoyi.web.controller.web;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.property.service.ContractConfigService;
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
import com.ruoyi.property.domain.ContractConfig;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 合同配置Controller
 *
 * @author wind
 * @date 2022-10-10
 */
@Api(tags = "合同配置")
@RestController
@RequestMapping("/hcx/property/config")
public class ContractConfigController extends BaseController {
    @Autowired
    private ContractConfigService contractConfigService;

    /**
     * 查询合同配置列表
     */
    @ApiOperation("查询合同配置列表")
    @PreAuthorize("@ss.hasPermi('property:config:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContractConfig contractConfig) {
        startPage();
        List<ContractConfig> list = contractConfigService.selectContractConfigList(contractConfig);
        return getDataTable(list);
    }

    /**
     * 导出合同配置列表
     */
    @ApiOperation("导出合同配置列表")
    @PreAuthorize("@ss.hasPermi('property:config:export')")
    @Log(title = "合同配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContractConfig contractConfig) {
        List<ContractConfig> list = contractConfigService.selectContractConfigList(contractConfig);
        ExcelUtil<ContractConfig> util = new ExcelUtil<ContractConfig>(ContractConfig.class);
        util.exportExcel(response, list, "合同配置数据");
    }

    /**
     * 获取合同配置详细信息
     */
    @ApiOperation("获取合同配置详细信息")
    @PreAuthorize("@ss.hasPermi('property:config:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(contractConfigService.selectContractConfigById(id));
    }

    /**
     * 新增合同配置
     */
    @ApiOperation("新增合同配置")
    @PreAuthorize("@ss.hasPermi('property:config:add')")
    @Log(title = "合同配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContractConfig contractConfig) {
        return toAjax(contractConfigService.insertContractConfig(contractConfig));
    }

    /**
     * 修改合同配置
     */
    @ApiOperation("修改合同配置")
    @PreAuthorize("@ss.hasPermi('property:config:edit')")
    @Log(title = "合同配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContractConfig contractConfig) {
        return toAjax(contractConfigService.updateContractConfig(contractConfig));
    }

    /**
     * 删除合同配置
     */
    @ApiOperation("删除合同配置")
    @PreAuthorize("@ss.hasPermi('property:config:remove')")
    @Log(title = "合同配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(contractConfigService.deleteContractConfigByIds(ids));
    }
}

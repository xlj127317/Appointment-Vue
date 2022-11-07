package com.ruoyi.web.controller.web;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.domain.Receivable;
import com.ruoyi.property.dto.ReceivableListInput;
import com.ruoyi.property.dto.ReceivableOutput;
import com.ruoyi.property.service.IContractService;
import com.ruoyi.property.service.IFeeTradeService;
import com.ruoyi.property.service.IPaymentTypeService;
import com.ruoyi.property.service.IReceivableService;
import com.ruoyi.web.dto.ReceivableCreateInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final static String FEE_TRADE_RECEIVABLE_SCOPE = "receivable";

    @Autowired
    private IReceivableService receivableService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IPaymentTypeService paymentTypeService;

    @Autowired
    private IFeeTradeService feeTradeService;

    /**
     * 表查询应收管理列
     */
    @ApiOperation("表查询应收管理列")
    @PreAuthorize("@ss.hasPermi('property:receivable:list')")
    @PostMapping("/list")
    public TableDataInfo list(@RequestBody ReceivableListInput input) {
        startPage();
        ModelMapper modelMapper = new ModelMapper();
        List<Receivable> list = receivableService.selectReceivableList(input);
        List<ReceivableOutput> output = list.stream()
                .map(r -> modelMapper.map(r, ReceivableOutput.class))
                .collect(Collectors.toList());
        return getDataTable(output);
    }

    /**
     * 导出应收管理列表
     */
    @ApiOperation("导出应收管理列表")
    @PreAuthorize("@ss.hasPermi('property:receivable:export')")
    @Log(title = "应收管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ReceivableListInput input) {
        List<Receivable> list = receivableService.selectReceivableList(input);
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
    @PreAuthorize("@ss.hasPermi('property:receivable:add')")
    @Log(title = "应收管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ReceivableCreateInput input) {
        Contract contract = contractService.selectContractById(input.getContractId());
        if (contract == null) {
            return error("合同不存在");
        }

        PaymentType paymentType = paymentTypeService.selectPaymentTypeById(input.getPaymentTypeId());
        if (paymentType == null) {
            return error("款项类别不存在");
        }

        BigDecimal itemPrice;
        BigDecimal itemCount = input.getItemCount();
        BigDecimal itemAmount;

        if (input.getPaymentTypeId() == null) {
            return error("款项类型不能为空");
        } else if (input.getPaymentTypeId().equals("1")) {
            // 水费
            itemPrice = BigDecimal.valueOf(2);
        } else if (input.getPaymentTypeId().equals("2")) {
            // 电费
            itemPrice = BigDecimal.valueOf(5);
        } else {
            itemAmount = input.getReceivableMoney();
            if (itemAmount == null) {
                return error("应收金额不能为空");
            }
            itemCount = BigDecimal.ONE;
            itemPrice = itemAmount;
        }

        if (itemCount == null || itemCount.compareTo(BigDecimal.ZERO) <= 0) {
            return error("数量不能为0");
        }

        itemAmount = itemPrice.multiply(itemCount);

        Receivable receivable = new Receivable();
        receivable.setContractId(contract.getId());
        receivable.setOwnerId(contract.getOwnerId());
        receivable.setPaymentId(input.getPaymentTypeId());
        receivable.setPaymentDate(input.getPaymentDate());
        receivable.setPaymentName(input.getPaymentName());
        receivable.setReceivableMoney(itemAmount.toString());
        receivable.setItemCount(itemCount);
        receivable.setItemPrice(itemPrice);
        receivable.setPaymentContent(input.getPaymentContent());
        receivable.setStopTime(input.getStopTime());
        receivable.setPaymentType(0);
        receivable.setCreateId(getUserId());
        int result = receivableService.insertReceivable(receivable);

        Map feeTradeCreateParams = new HashMap<String, Object>();
        feeTradeCreateParams.put("ownerId", contract.getOwnerId());
        feeTradeCreateParams.put("subject", input.getPaymentName());
        feeTradeCreateParams.put("description", input.getPaymentContent());
        feeTradeCreateParams.put("price", itemPrice);
        feeTradeCreateParams.put("count", itemCount);
        feeTradeCreateParams.put("outScope", FEE_TRADE_RECEIVABLE_SCOPE);
        feeTradeCreateParams.put("outId", receivable.getId());
        feeTradeCreateParams.put("contractId", receivable.getContractId());
        feeTradeService.createTrade(feeTradeCreateParams);

        return toAjax(result);
    }

    /**
     * 修改应收管理
     */
    @ApiOperation("修改应收管理")
    @PreAuthorize("@ss.hasPermi('property:receivable:edit')")
    @Log(title = "应收管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ReceivableCreateInput input) {
        if (input.getId() == null) {
            return error("id不能为空");
        }

        Map params = new HashMap();
        params.put("id", input.getId());
        params.put("lockMode", SqlLockMode.UPDATE);
        Receivable receivable = receivableService.findOne(params);
        if (receivable == null) {
            return error("应收记录不存在");
        }

        if (receivable.getActuallyStatus().equals(1)) {
            return error("已实收不可修改");
        }

        if (input.getContractId() != null && !input.getContractId().equals(receivable.getContractId())) {
            return error("不可修改合同");
        }

        if (input.getPaymentTypeId() != null && !input.getPaymentTypeId().equals(receivable.getPaymentId())) {
            return error("不可修改款项类别");
        }

        Contract contract = contractService.selectContractById(receivable.getContractId());
        if (contract == null) {
            return error("合同不存在");
        }

        String paymentTypeId = receivable.getPaymentId();
        BigDecimal itemPrice;
        BigDecimal itemCount = input.getItemCount();
        BigDecimal itemAmount;

        if (paymentTypeId == null) {
            return error("款项类型不能为空");
        } else if (paymentTypeId.equals("1")) {
            // 水费
            itemPrice = BigDecimal.valueOf(2);
        } else if (paymentTypeId.equals("2")) {
            // 电费
            itemPrice = BigDecimal.valueOf(5);
        } else {
            itemAmount = input.getReceivableMoney();
            if (itemAmount == null) {
                return error("应收金额不能为空");
            }
            itemCount = BigDecimal.ONE;
            itemPrice = itemAmount;
        }

        if (itemCount == null || itemCount.compareTo(BigDecimal.ZERO) <= 0) {
            return error("数量不能为0");
        }

        itemAmount = itemPrice.multiply(itemCount);

        receivable.setContractId(contract.getId());
        receivable.setOwnerId(contract.getOwnerId());
        receivable.setPaymentId(input.getPaymentTypeId());
        receivable.setPaymentDate(input.getPaymentDate());
        receivable.setPaymentName(input.getPaymentName());
        receivable.setReceivableMoney(itemAmount.toString());
        receivable.setItemCount(itemCount);
        receivable.setItemPrice(itemPrice);
        receivable.setPaymentContent(input.getPaymentContent());
        receivable.setStopTime(input.getStopTime());

        receivableService.updateReceivable(receivable);

        Map findFeeTradeParams = new HashMap();
        findFeeTradeParams.put("outScope", FEE_TRADE_RECEIVABLE_SCOPE);
        findFeeTradeParams.put("outId", receivable.getId());
        findFeeTradeParams.put("lockMode", SqlLockMode.UPDATE);
        Map feeTradeMap = feeTradeService.getTrade(findFeeTradeParams);
        if (feeTradeMap == null) {
            return error("原账单记录不存在");
        }
        Integer feeTradeState = (Integer)feeTradeMap.get("state");
        if (!feeTradeState.equals(FeeTradeState.WAIT_PAY.getValue())) {
            return error("账单已被锁定");
        }
        feeTradeService.deleteTrade((String)feeTradeMap.get("id"));

        Map feeTradeCreateParams = new HashMap<String, Object>();
        feeTradeCreateParams.put("ownerId", contract.getOwnerId());
        feeTradeCreateParams.put("subject", input.getPaymentName());
        feeTradeCreateParams.put("description", input.getPaymentContent());
        feeTradeCreateParams.put("price", itemPrice);
        feeTradeCreateParams.put("count", itemCount);
        feeTradeCreateParams.put("outScope", FEE_TRADE_RECEIVABLE_SCOPE);
        feeTradeCreateParams.put("outId", receivable.getId());
        feeTradeService.createTrade(feeTradeCreateParams);

        return success();
    }

    /**
     * 删除应收管理
     */
    @ApiOperation("删除应收管理")
    @PreAuthorize("@ss.hasPermi('property:receivable:remove')")
    @Log(title = "应收管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        for (String id : ids) {
            Map params = new HashMap();
            params.put("id", id);
            params.put("lockMode", SqlLockMode.UPDATE);
            Receivable receivable = receivableService.findOne(params);
            if (receivable == null) {
                return error("应收记录不存在");
            }

            if (receivable.getActuallyStatus().equals(1)) {
                return error("已实收不可删除");
            }

            Map findFeeTradeParams = new HashMap();
            findFeeTradeParams.put("outScope", FEE_TRADE_RECEIVABLE_SCOPE);
            findFeeTradeParams.put("outId", receivable.getId());
            findFeeTradeParams.put("lockMode", SqlLockMode.UPDATE);
            Map feeTradeMap = feeTradeService.getTrade(findFeeTradeParams);
            if (feeTradeMap == null) {
                return error("原账单记录不存在");
            }
            Integer feeTradeState = (Integer)feeTradeMap.get("state");
            if (!feeTradeState.equals(FeeTradeState.WAIT_PAY.getValue())) {
                return error("账单已被锁定");
            }
            feeTradeService.deleteTrade((String)feeTradeMap.get("id"));

            receivableService.deleteReceivableById(id);
        }
        return success();
    }

    @GetMapping("/chart")
    public AjaxResult chart() {
        return AjaxResult.success(receivableService.getAmountValueChart(Year.now().getValue()));
    }
}

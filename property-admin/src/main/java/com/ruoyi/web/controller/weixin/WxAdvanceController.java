package com.ruoyi.web.controller.weixin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.dto.payment.UnifiedOrderOutput;
import com.ruoyi.property.dto.wx.AdvanceCreateInput;
import com.ruoyi.property.dto.wx.AdvanceGetInput;
import com.ruoyi.property.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/hcx/property/wx/advances")
public class WxAdvanceController extends BaseController {
    private final static String AdvanceScope = "advance";
    @Autowired
    private IAdvanceService advanceService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private IPaymentTypeService paymentTypeService;

    @Autowired
    private IReceivableService receivableService;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IFeeTradeService feeTradeService;

    @Autowired
    private IEasyTrService easyTrService;

    @Autowired
    private RedisCache cache;

    @Autowired
    private WxFeeTradesController wxFeeTradesController;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @PostMapping("")
    public AjaxResult create(
            @RequestBody AdvanceCreateInput input,
            HttpServletRequest request) throws Exception {
        if (input == null) {
            return error("无效的请求");
        }
        if (input.getAdvanceMoney() == null
                || input.getAdvanceMoney().compareTo(BigDecimal.valueOf(1, 2)) <= 0) {
            return error("预收金额不能少于0.01元");
        }

        String wxOpenId = getUserId();
        String ownerId = easyTrService.mustUserIdToOwnerId(wxOpenId);
        String remoteAddr = request.getRemoteAddr();

        Contract contract = contractService.newQuery()
                .id(input.getContractId())
                .ownerId(ownerId)
                .lockMode(SqlLockMode.SHARE)
                .findOne();
        if (contract == null) {
            return error("合同不存在");
        }

        PaymentType paymentType = paymentTypeService.selectPaymentTypeById(input.getPaymentTypeId());
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
        advance.setId(PkeyGenerator.getUniqueString());
        advance.setDeductStatus(0);
        advance.setOwnerId(ownerId);

        UnifiedOrderOutput unifiedOrderOutput = paymentService.unifiedOrder()
                .body(advance.getPaymentName() + "-" + advance.getPaymentContent())
                .outTradeNo(PkeyGenerator.getUniqueString())
                .totalFee(input.getAdvanceMoney())
                .spbillCreateIp(remoteAddr)
                .openId(wxOpenId)
                .extra("advance", advance)
                .send();
        return AjaxResult.success(unifiedOrderOutput);
    }

    @PostMapping("/list")
    public AjaxResult list() throws Exception {
        String wxOpenId = getUserId();
        String ownerId = easyTrService.mustUserIdToOwnerId(wxOpenId);
        List<Advance> advances = advanceService.newQuery()
                .ownerId(ownerId)
                .find();
        return AjaxResult.success(advances);
    }

    @PostMapping("/get")
    public AjaxResult get(@RequestBody AdvanceGetInput input) throws Exception {
        if (input.getId() == null) {
            return error("预收id不能为空");
        }

        String wxOpenId = getUserId();
        String ownerId = easyTrService.mustUserIdToOwnerId(wxOpenId);

        Advance advance = advanceService.newQuery()
                .ownerId(ownerId)
                .findOneOrNull();

        if (advance == null) {
            return error("预收款项不存在");
        }

        return AjaxResult.success(advance);
    }
}

package com.ruoyi.web.controller.weixin;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.SqlLockMode;
import com.ruoyi.common.utils.uuid.PkeyGenerator;
import com.ruoyi.property.domain.Advance;
import com.ruoyi.property.domain.Contract;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.domain.PaymentType;
import com.ruoyi.property.dto.payment.UnifiedOrderInput;
import com.ruoyi.property.dto.payment.UnifiedOrderOutput;
import com.ruoyi.property.dto.wx.AdvanceCreateInput;
import com.ruoyi.property.dto.wx.UnifiedOrderInputDto;
import com.ruoyi.property.dto.wx.UnifiedOrderOutputDto;
import com.ruoyi.property.service.*;
import org.apache.ibatis.transaction.Transaction;
import org.checkerframework.checker.units.qual.A;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
        getLoginUser().setUserId("oyM1S41LqTlqnbX_oIK5hjr70Efw");
        String wxOpenId = getUserId();
        String ownerId = easyTrService.mustUserIdToOwnerId(wxOpenId);
        String remoteAddr = request.getRemoteAddr();

        Contract contract = contractService.newQueryBuilder()
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
                .totalFee(input.getAdvanceMoney().movePointRight(2))
                .spbillCreateIp(remoteAddr)
                .openId(wxOpenId)
                .extra("advance", advance)
                .send();
        return AjaxResult.success(unifiedOrderOutput);
    }
}

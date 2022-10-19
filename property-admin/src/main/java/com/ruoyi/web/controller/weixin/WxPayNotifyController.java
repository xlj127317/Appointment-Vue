package com.ruoyi.web.controller.weixin;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.DbLockStrength;
import com.ruoyi.common.enums.FeeTradeState;
import com.ruoyi.property.domain.FeeTrade;
import com.ruoyi.property.dto.wx.FeeTradeOutputDto;
import com.ruoyi.property.events.FeeTradeCompletedEvent;
import com.ruoyi.property.service.IFeeTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hcx/property/wx/pay/notify")
@Anonymous
public class WxPayNotifyController extends BaseController {
    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private IFeeTradeService feeTradeService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/completeOrder")
    public String completeOrder(@RequestBody String xmlData) {
        logger.info(xmlData);

        try {
            final WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);
            String feeTradeNo = notifyResult.getAttach();
            Map feeTradeMap = feeTradeService.getTradeByNo(feeTradeNo, DbLockStrength.UPDATE);
            FeeTradeOutputDto feeTrade = BeanUtil.mapToBean(feeTradeMap, FeeTradeOutputDto.class, true, CopyOptions.create());
            if (feeTrade.getState() != FeeTradeState.WAIT_PAY) {
                logger.warn("账单[{}]已支付，关联支付单号{}，自动发起退款", feeTradeNo, notifyResult.getOutTradeNo());
                return WxPayNotifyResponse.success("处理成功！");
            }

            // notifyResult.getT

            Map params = new HashMap();
            params.put("paidAmount", feeTrade.getAmount());
            params.put("bizChannel", FeeTrade.BIZ_CHANNEL_WEIXIN_PAY);
            params.put("bizTradeNo", notifyResult.getOutTradeNo());
            params.put("stateSucceedValue", FeeTradeState.SUCCEED.getValue());
            feeTradeService.completeTrade(params);

            FeeTradeCompletedEvent event = new FeeTradeCompletedEvent();
            event.setId(feeTrade.getId());
            event.setBizChannel(feeTrade.getBizChannel());
            event.setOutScope((String)feeTradeMap.get("out_scope"));
            event.setOutId((String)feeTradeMap.get("out_id"));
            eventPublisher.publishEvent(event);

            //WxPayRefundRequest.newBuilder().

            return WxPayNotifyResponse.success("处理成功！");
        } catch (Exception exception) {
            return WxPayNotifyResponse.fail(exception.getMessage());
        }
    }
}

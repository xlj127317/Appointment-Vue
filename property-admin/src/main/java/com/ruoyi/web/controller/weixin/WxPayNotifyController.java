package com.ruoyi.web.controller.weixin;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.config.RabbitConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.property.dto.wx.PayNotifyDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hcx/property/wx/pay/notify")
@Anonymous
public class WxPayNotifyController extends BaseController {
    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/pay")
    public String pay(@RequestBody String xmlData) throws InterruptedException {
        logger.info(xmlData);

        try {
            final WxPayOrderNotifyResult notifyResult = wxPayService.parseOrderNotifyResult(xmlData);

            PayNotifyDto dto = new PayNotifyDto();
            dto.setResultCode(notifyResult.getResultCode());
            dto.setErrCode(notifyResult.getErrCode());
            dto.setErrCodeDes(notifyResult.getErrCodeDes());
            dto.setAttach(notifyResult.getAttach());
            dto.setOpenId(notifyResult.getOpenid());
            dto.setTotalFee(notifyResult.getTotalFee());
            dto.setCashFee(notifyResult.getCashFee());
            dto.setTransactionId(notifyResult.getTransactionId());
            dto.setOutTradeNo(notifyResult.getOutTradeNo());
            dto.setTimeEnd(notifyResult.getTimeEnd());
            rabbitTemplate.convertAndSend(RabbitConfig.TRANSACTION_QUEUE, dto);
            return WxPayNotifyResponse.success("OK");
        } catch (Exception exception) {
            return WxPayNotifyResponse.fail(exception.getMessage());
        }
    }

    @PostMapping("/refund")
    public String refund(@RequestBody String xmlData) {
        logger.info("WxPay:refund, {}", xmlData);
        logger.info(xmlData);
        return WxPayNotifyResponse.success("OK");
    }
}

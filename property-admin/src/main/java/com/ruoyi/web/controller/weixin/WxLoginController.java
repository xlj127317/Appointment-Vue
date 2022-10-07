package com.ruoyi.web.controller.weixin;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.framework.web.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 心风
 * @date 2022/10/06 10:21
 **/
@Api(tags = "微信登录")
@RestController
@RequestMapping("/hcx/property/wx")
public class WxLoginController {
    @Autowired
    private SysLoginService loginService;

    @ApiOperation("微信登录")
    @GetMapping("/login")
    public AjaxResult wxLogin(String code) {
        return loginService.wxLogin(code);
    }
}

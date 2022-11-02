package com.ruoyi.web.controller.weixin;

import com.ruoyi.property.dto.wx.AdvanceCreateInput;
import com.ruoyi.property.service.IAdvanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hcx/property/wx/advances")
public class WxAdvanceController {
    @Autowired
    private IAdvanceService advanceService;

    @PostMapping("")
    public void create(AdvanceCreateInput input) {

    }
}

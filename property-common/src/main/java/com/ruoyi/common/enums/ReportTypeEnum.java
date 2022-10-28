package com.ruoyi.common.enums;

/**
 * 工单类别枚举类
 *
 * @author 心风
 * @date 2022/10/13 11:19
 **/
public enum ReportTypeEnum {

    FURNISH("装修办理"),
    REPAIR("物业报修"),
    THING_OUT("物品出入"),
    VISIT("来访出入"),
    PARK_RESERVE("园区资源预约");

    private final String value;

    ReportTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

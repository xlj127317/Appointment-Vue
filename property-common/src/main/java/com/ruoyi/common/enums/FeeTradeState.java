package com.ruoyi.common.enums;

public enum FeeTradeState {
    WAIT_PAY(0),
    CLOSED(1),
    SUCCEED(2),
    FINISHED(3),
    ;

    private final int value;

    FeeTradeState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

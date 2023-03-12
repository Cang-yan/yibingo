package com.yibingo.race.core.service.enums;

public enum MessageEnum {
    APPLY(0,"申请消息"),
    REMIND(1,"提醒消息"),
    WARN(2,"异常消息");

    private Integer status;

    private String msg;

    MessageEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}

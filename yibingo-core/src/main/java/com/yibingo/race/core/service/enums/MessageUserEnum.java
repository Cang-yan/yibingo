package com.yibingo.race.core.service.enums;

public enum MessageUserEnum {

    UNREAD(0,"未读"),
    READ(1,"已读"),
    PENDING(2,"待处理"),
    ACCESS(3,"已同意"),
    DENY(4,"拒绝加入");

    private Integer status;
    private String msg;

    MessageUserEnum(Integer status, String msg) {
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

package com.yibingo.race.core.service.enums;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/5/31 19:15
 */
public enum FormRecordsEnum {
    UNDONE(0,"未完成"),
    DONE(1,"正常"),
    ABNOMAL(2,"出现异常")


    ;


    private Integer status;
    private String msg;

    FormRecordsEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.yibingo.race.core.service.enums;

public enum FormRemindEnum {
    FIVE_MINUTE(0,Integer.toUnsignedLong(300000)),
    TEN_MINUTE(1,Integer.toUnsignedLong(600000)),
    FIFTEEN_MINUTE(2,Integer.toUnsignedLong(900000))
    ;


    private Integer type;
    private Long ms_num;//毫秒数


    FormRemindEnum(Integer value, Long ms_num) {
        this.type = value;
        this.ms_num = ms_num;
    }
    public Integer getValue() {
        return type;
    }

    public Long getMs_num() {
        return ms_num;
    }

    public static Long getMsNum(Integer type){
        for (FormRemindEnum enums : FormRemindEnum.values()){
            if (type.equals(enums.type)){
                return enums.ms_num;
            }


        }

        return Integer.toUnsignedLong(0);
    }
}

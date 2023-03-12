package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.FormCount;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 表单计数PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormCountPutMapper{

    private String formId;
    private Integer undone;
    private Integer done;
    private Integer abnormal;

    public static FormCount convertToEntity(FormCountPutMapper putMapper){
        FormCount entity = new FormCount();
        entity.setFormId(putMapper.getFormId());
        entity.setUndone(putMapper.getUndone());
        entity.setDone(putMapper.getDone());
        entity.setAbnormal(putMapper.getAbnormal());
        return entity;
    }



}
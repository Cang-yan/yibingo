package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.FormCountTuple;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 表单元组统计PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormCountTuplePutMapper{

    private String formId;
    private String tupleId;
    private Integer undone;
    private Integer done;
    private Integer abnormal;

    public static FormCountTuple convertToEntity(FormCountTuplePutMapper putMapper){
        FormCountTuple entity = new FormCountTuple();
        entity.setFormId(putMapper.getFormId());
        entity.setTupleId(putMapper.getTupleId());
        entity.setUndone(putMapper.getUndone());
        entity.setDone(putMapper.getDone());
        entity.setAbnormal(putMapper.getAbnormal());
        return entity;
    }



}
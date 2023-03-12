package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.FormLabel;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 表单标签PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormLabelPutMapper{

    private String title;

    public static FormLabel convertToEntity(FormLabelPutMapper putMapper){
        FormLabel entity = new FormLabel();
        entity.setTitle(putMapper.getTitle());
        return entity;
    }



}
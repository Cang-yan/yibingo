package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.FormLabelImg;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 图片标签PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormLabelImgPutMapper{

    private String title;

    public static FormLabelImg convertToEntity(FormLabelImgPutMapper putMapper){
        FormLabelImg entity = new FormLabelImg();
        entity.setTitle(putMapper.getTitle());
        return entity;
    }



}
package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.FormImg;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 表单图片选择PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormImgPutMapper{

    private Integer healthyCode;
    private Integer tarvelCard;

    public static FormImg convertToEntity(FormImgPutMapper putMapper){
        FormImg entity = new FormImg();
        entity.setHealthyCode(putMapper.getHealthyCode());
        entity.setTarvelCard(putMapper.getTarvelCard());
        return entity;
    }



}
package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.FormImg;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 表单图片选择DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormImgDetailMapper{


    public static Map<String,Object> buildMap(FormImg entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("healthyCode",entity.getHealthyCode());
        map.put("tarvelCard",entity.getTarvelCard());
        return map;
    }








}
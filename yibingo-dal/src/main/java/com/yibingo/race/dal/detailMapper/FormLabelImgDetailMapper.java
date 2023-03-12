package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.FormLabelImg;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 图片标签DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormLabelImgDetailMapper{


    public static Map<String,Object> buildMap(FormLabelImg entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("title",entity.getTitle());
        return map;
    }








}
package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.ImgHealthCode;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 健康码图片DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class ImgHealthCodeDetailMapper{


    public static Map<String,Object> buildMap(ImgHealthCode entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("formRecordsId",entity.getFormRecordsId());
        map.put("url",entity.getUrl());
        map.put("color",entity.getColor());
        map.put("acidType",entity.getAcidType());
        map.put("acidTime",entity.getAcidTime());
        map.put("vaccinesCount",entity.getVaccinesCount());
        map.put("status",entity.getStatus());
        return map;
    }








}
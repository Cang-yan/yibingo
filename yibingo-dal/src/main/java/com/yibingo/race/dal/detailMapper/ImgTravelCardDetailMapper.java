package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.ImgTravelCard;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 行程卡照片DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class ImgTravelCardDetailMapper{


    public static Map<String,Object> buildMap(ImgTravelCard entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("formRecordsId",entity.getFormRecordsId());
        map.put("url",entity.getUrl());
        map.put("color",entity.getColor());
        map.put("isStar",entity.getIsStar());
        map.put("travelRecords",entity.getTravelRecords());
        map.put("riskArea",entity.getRiskArea());
        return map;
    }








}
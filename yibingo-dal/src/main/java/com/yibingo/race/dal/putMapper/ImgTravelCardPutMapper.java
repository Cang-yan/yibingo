package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.ImgTravelCard;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 行程卡照片PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class ImgTravelCardPutMapper{

    private String formRecordsId;
    private String url;
    private String color;
    private Integer isStar;
    private String travelRecords;
    private String riskArea;

    public static ImgTravelCard convertToEntity(ImgTravelCardPutMapper putMapper){
        ImgTravelCard entity = new ImgTravelCard();
        entity.setFormRecordsId(putMapper.getFormRecordsId());
        entity.setUrl(putMapper.getUrl());
        entity.setColor(putMapper.getColor());
        entity.setIsStar(putMapper.getIsStar());
        entity.setTravelRecords(putMapper.getTravelRecords());
        entity.setRiskArea(putMapper.getRiskArea());
        return entity;
    }



}
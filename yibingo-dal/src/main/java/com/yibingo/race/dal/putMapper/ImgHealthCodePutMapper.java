package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.ImgHealthCode;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 健康码图片PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class ImgHealthCodePutMapper{

    private String formRecordsId;
    private String url;
    private String color;
    private String acidType;
    private String acidTime;
    private String vaccinesCount;
    private Integer status;

    public static ImgHealthCode convertToEntity(ImgHealthCodePutMapper putMapper){
        ImgHealthCode entity = new ImgHealthCode();
        entity.setFormRecordsId(putMapper.getFormRecordsId());
        entity.setUrl(putMapper.getUrl());
        entity.setColor(putMapper.getColor());
        entity.setAcidType(putMapper.getAcidType());
        entity.setAcidTime(putMapper.getAcidTime());
        entity.setVaccinesCount(putMapper.getVaccinesCount());
        entity.setStatus(putMapper.getStatus());
        return entity;
    }



}
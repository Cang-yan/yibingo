package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.FormRecords;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 表单记录PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormRecordsPutMapper{

    private String formId;
    private String organizationId;
    private String tupleId;
    private String userId;
    private Integer status;
    private Map<String,Object> labelCombination;
    private Map<String,Object> labelCombinationImg;
    private String healthyCodeUrl;
    private String travelCardUrl;

    public static FormRecords convertToEntity(FormRecordsPutMapper putMapper){
        FormRecords entity = new FormRecords();
        entity.setFormId(putMapper.getFormId());
        entity.setOrganizationId(putMapper.getOrganizationId());
        entity.setTupleId(putMapper.getTupleId());
        entity.setUserId(putMapper.getUserId());
        entity.setStatus(putMapper.getStatus());
        entity.setLabelCombination(putMapper.getLabelCombination());
        entity.setLabelCombinationImg(putMapper.getLabelCombinationImg());
        entity.setHealthyCodeUrl(putMapper.getHealthyCodeUrl());
        entity.setTravelCardUrl(putMapper.getTravelCardUrl());
        return entity;
    }



}
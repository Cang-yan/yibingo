package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.FormRecords;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 表单记录DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-26 20:03:13
 */
@Data
public class FormRecordsDetailMapper{


    public static Map<String,Object> buildMap(FormRecords entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("formId",entity.getFormId());
        map.put("organizationId",entity.getOrganizationId());
        map.put("tupleId",entity.getTupleId());
        map.put("userId",entity.getUserId());
        map.put("status",entity.getStatus());
        map.put("labelCombination",entity.getLabelCombination());
        map.put("labelCombinationImg",entity.getLabelCombinationImg());
        map.put("healthyCodeUrl",entity.getHealthyCodeUrl());
        map.put("travelCardUrl",entity.getTravelCardUrl());
        return map;
    }








}
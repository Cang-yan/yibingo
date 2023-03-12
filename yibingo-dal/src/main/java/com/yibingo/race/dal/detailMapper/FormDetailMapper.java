package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.Form;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 表单发布DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-06-28 19:35:54
 */
@Data
public class FormDetailMapper{


    public static Map<String,Object> buildMap(Form entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("createUserId",entity.getCreateUserId());
        map.put("isEnd",entity.getIsEnd());
        map.put("title",entity.getTitle());
        map.put("notes",entity.getNotes());
        map.put("labelCombination",entity.getLabelCombination());
        map.put("labelCombinationImg",entity.getLabelCombinationImg());
        map.put("healthyCode",entity.getHealthyCode());
        map.put("travelCard",entity.getTravelCard());
        map.put("organizationId",entity.getOrganizationId());
        map.put("beginTime",entity.getBeginTime());
        map.put("endTime",entity.getEndTime());
        map.put("attentionMode",entity.getAttentionMode());
        map.put("acidRequirement",entity.getAcidRequirement());
        map.put("scheduleRemind",entity.getScheduleRemind());
        return map;
    }








}
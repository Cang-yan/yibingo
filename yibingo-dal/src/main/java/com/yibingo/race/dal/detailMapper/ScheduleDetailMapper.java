package com.yibingo.race.dal.detailMapper;

import com.yibingo.race.dal.entity.Schedule;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Date;


/**
 * 定时任务表DetailMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-19 20:48:42
 */
@Data
public class ScheduleDetailMapper{


    public static Map<String,Object> buildMap(Schedule entity){
        Map<String,Object> map = new HashMap<>();
        map.put("id",entity.getId());
        map.put("createTime",entity.getCreateTime());
        map.put("updateTime",entity.getUpdateTime());
        map.put("lastScheduleTime",entity.getLastScheduleTime());
        map.put("relationId",entity.getRelationId());
        return map;
    }








}
package com.yibingo.race.dal.putMapper;

import com.yibingo.race.dal.entity.Schedule;


import lombok.Data;
import java.util.Map;
import java.util.Date;

/**
 * 定时任务表PutMapper
 *
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-19 20:48:42
 */
@Data
public class SchedulePutMapper{

    private Date lastScheduleTime;
    private String relationId;

    public static Schedule convertToEntity(SchedulePutMapper putMapper){
        Schedule entity = new Schedule();
        entity.setLastScheduleTime(putMapper.getLastScheduleTime());
        entity.setRelationId(putMapper.getRelationId());
        return entity;
    }



}
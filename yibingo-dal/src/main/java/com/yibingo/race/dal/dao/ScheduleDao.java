package com.yibingo.race.dal.dao;

import com.yibingo.race.dal.entity.Schedule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 定时任务表
 * 
 * @author Yang Xin
 * @email little-cy@qq.com
 * @date 2022-05-19 20:48:42
 */
@Mapper
public interface ScheduleDao extends BaseMapper<Schedule> {
	
}

package com.yibingo.race.core.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import java.util.Date;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.yibingo.race.dal.dao.ScheduleDao;
import com.yibingo.race.dal.entity.Schedule;
import com.yibingo.race.core.service.ScheduleService;
import com.yibingo.race.dal.filterMapper.ScheduleFilterMapper;



@Service("scheduleService")
public class ScheduleServiceImpl extends ServiceImpl<ScheduleDao, Schedule> implements ScheduleService {


}
package com.yibingo.race.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yibingo.race.quartz.entity.ScheduleJob;
import com.yibingo.race.quartz.dao.ScheduleJobMapper;
import org.springframework.stereotype.Service;

@Service
public class ScheduleJobService extends ServiceImpl<ScheduleJobMapper, ScheduleJob> {

}

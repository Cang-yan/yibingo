package com.yibingo.race.quartz.task;

import com.yibingo.race.core.service.MessageExtendsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/6/21 19:31
 */
@Slf4j
@Component("createRemindMessageTask")
public class CreateRemindMessageTaskImpl implements BaseTaskService{

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

    @Autowired
    MessageExtendsService messageExtendsService;

    @Override
    public void run(String params) throws Exception {
        log.info("JOB, Schedule remind.");
        messageExtendsService.createBatchRemindMessage();

    }
}

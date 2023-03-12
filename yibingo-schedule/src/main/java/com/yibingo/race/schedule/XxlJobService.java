package com.yibingo.race.schedule;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.yibingo.race.core.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Yang Xin
 * @time: 2022/5/23 21:52
 */
@Slf4j
@Component
public class XxlJobService {

    @Autowired
    MessageService messageService;
    @Autowired
    MessageExtendsService messageExtendsService;
    @Autowired
    MessageUserService messageUserService;
    @Autowired
    MessageUserExtendsService messageUserExtendsService;
    @Autowired
    FormService formService;
    @Autowired
    FormExtendsService formExtendsService;
    @Autowired
    FormRecordsExtendsService formRecordsExtendsService;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        log.info("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            log.info("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }


    /*
     * todo 定时任务设置在这里
     *
     * 定时通知，在规定时间内进行通知
     *
     * https://blog.csdn.net/weixin_43470118/article/details/122277448?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165330656616781685367254%2522%252C%2522scm%2522%253A%252220140713.130102334..%2522%257D&request_id=165330656616781685367254&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduend~default-1-122277448-null-null.142^v10^control,157^v8^control&utm_term=springboot%E9%9B%86%E6%88%90xxljob&spm=1018.2226.3001.4187
     * */
    @XxlJob("notifyJobHandler")
    public ReturnT<String> notifyJobHandler(String param) throws Exception {
        log.info("XXL-JOB, Schedule remind.");
        messageExtendsService.createBatchRemindMessage();

        return ReturnT.SUCCESS;

    }


}

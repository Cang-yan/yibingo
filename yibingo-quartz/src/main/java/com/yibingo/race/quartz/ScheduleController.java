package com.yibingo.race.quartz;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yibingo.race.quartz.entity.ScheduleJob;
import com.yibingo.race.quartz.schedule.ScheduleHandler;
import com.yibingo.race.quartz.service.ScheduleJobService;
import com.yibingo.race.quartz.service.ScheduleLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.quartz.Scheduler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Api(
        value = "schedule",
        tags = "定时任务"
)
@RestController
@RequestMapping("job/sc")
public class ScheduleController {
    @Resource
    ScheduleJobService jobService;
    @Resource
    ScheduleLogService logService;
    @Resource
    Scheduler scheduler;


    /*
    * 查找定时任务
    *
    * */
    @ApiOperation(
            value = "查找定时任务",
            notes = "查找定时任务"
    )
    @RequestMapping(
            value = "/task_search",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Object> taskSearch() {
        List<ScheduleJob> jobList = jobService.list();

        return ResponseEntity.ok(jobList);
    }

    /**
     * 根据任务ID运行一个任务
     *
     * @param jobId 任务ID
     * @return 运行状态
     */
    @ApiOperation(
            value = "根据任务ID运行一个任务",
            notes = "根据任务ID运行一个任务"
    )
    @RequestMapping(
            value = "/task_run",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Object> taskRun(String jobId) {
        ScheduleJob job = jobService.getById(jobId);
        ScheduleHandler.run(scheduler, job);
        return ResponseEntity.ok("成功");
    }

    /**
     * 保存任务
     *
     * @param job 任务实体
     * @return 保存结果
     */
    @ApiOperation(
            value = "保存任务",
            notes = "保存任务"
    )
    @RequestMapping(
            value = "/task_save",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<Object> taskSave(ScheduleJob job) {
        job.setId(IdWorker.getIdStr());
        job.setCreateTime(LocalDateTime.now());
        jobService.save(job);
        ScheduleHandler.createJob(scheduler, job);
        return ResponseEntity.ok("成功");
    }

    /**
     * 更新任务
     *
     * @param job 任务数据
     * @return 更新结果
     */
    @ApiOperation(
            value = "更新任务",
            notes = "更新任务"
    )
    @RequestMapping(
            value = "/task_update",
            method = RequestMethod.POST
    )
    @ResponseBody
    public ResponseEntity<Object> taskUpdate(ScheduleJob job) {
        jobService.updateById(job);
        ScheduleHandler.updateJob(scheduler, job);
        return ResponseEntity.ok("成功");
    }

    /**
     * 停止定时任务
     *
     * @param jobId 任务Id
     * @return 执行结果
     */
    @ApiOperation(
            value = "停止定时任务",
            notes = "停止定时任务"
    )
    @RequestMapping(
            value = "/task_pause",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Object> taskPause(String jobId) {
        ScheduleJob scheduleJob = jobService.getById(jobId);
        ScheduleHandler.pauseJob(scheduler, Long.parseLong(jobId));
        scheduleJob.setStatus("1");
        jobService.updateById(scheduleJob);
        return ResponseEntity.ok("成功");
    }

    /**
     * 恢复定时任务
     *
     * @param jobId 任务Id
     * @return 执行结果
     */
    @ApiOperation(
            value = "恢复定时任务",
            notes = "恢复定时任务"
    )
    @RequestMapping(
            value = "/task_resume",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Object> taskResume(String jobId) {
        ScheduleJob scheduleJob = jobService.getById(jobId);
        ScheduleHandler.resumeJob(scheduler, Long.parseLong(jobId));
        scheduleJob.setStatus("0");
        jobService.updateById(scheduleJob);
        return ResponseEntity.ok("成功");
    }

    /**
     * 删除一个任务
     *
     * @param jobId 任务ID
     * @return 删除结果
     */
    @ApiOperation(
            value = "删除一个任务",
            notes = "删除一个任务"
    )
    @RequestMapping(
            value = "/task_del",
            method = RequestMethod.GET
    )
    @ResponseBody
    public ResponseEntity<Object> taskDel(String jobId) {
        ScheduleHandler.deleteJob(scheduler, Long.parseLong(jobId));
        jobService.removeById(jobId);
        return ResponseEntity.ok("成功");
    }
}

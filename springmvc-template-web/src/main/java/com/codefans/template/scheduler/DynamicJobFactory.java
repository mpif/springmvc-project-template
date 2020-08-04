package com.codefans.template.scheduler;

import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: codefans
 * @Date: 2020-08-04 8:26
 */

@Component
public class DynamicJobFactory {

    @Resource
    private Scheduler scheduler;

    /**
     * 添加job
     *
     * @param className      类名
     * @param cronExpression cron表达式
     * @throws Exception
     */
    public void addJob(String className, String cronExpression) throws Exception {
        Class clazz = Class.forName(className);
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity("JobName:" + className, Scheduler.DEFAULT_GROUP)
                .withDescription("A test job")
                //如果需要给任务传递参数，可以用setJobData来设置参数
                .setJobData(new JobDataMap())
                .build();
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setName("JobTrigger:" + className);
        cronTrigger.setCronExpression(cronExpression);
        JobKey jobKey = new JobKey("JobName:" + className, Scheduler.DEFAULT_GROUP);
        if(!scheduler.checkExists(jobKey)){
            scheduler.scheduleJob(jobDetail, cronTrigger);
        }
    }

    /**
     * 暂停job
     *
     * @param className 类名
     * @throws Exception
     */
    public void pause(String className) throws Exception {
        JobKey jobKey = new JobKey("JobName:" + className, Scheduler.DEFAULT_GROUP);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 重启job
     *
     * @param className
     * @throws Exception
     */
    public void resume(String className) throws Exception {
        JobKey jobKey = new JobKey("JobName:" + className, Scheduler.DEFAULT_GROUP);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 停止job
     *
     * @param className
     * @throws Exception
     */
    public void stop(String className) throws Exception {
        JobKey jobKey = new JobKey("JobName:" + className, Scheduler.DEFAULT_GROUP);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 修改job的执行时间
     *
     * @param className
     * @param cronExpression
     * @throws Exception
     */
    public void updateJobTime(String className, String cronExpression) throws Exception {
        TriggerKey triggerKey = new TriggerKey("JobTrigger:" + className,Scheduler.DEFAULT_GROUP);
        CronTriggerImpl trigger = (CronTriggerImpl) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            return;
        }
        String oldTime = trigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(cronExpression)) {
            trigger.setCronExpression(cronExpression);
            scheduler.rescheduleJob(triggerKey, trigger);
        }
    }

    /**
     * 获取job信息
     * @param className
     * @return
     * @throws Exception
     */
    public JobDetail getJobDetail(String className) throws Exception {
        JobKey jobKey = new JobKey("JobName:" + className, Scheduler.DEFAULT_GROUP);
        JobDetail detail = scheduler.getJobDetail(jobKey);
        return detail;
    }

    /**
     * 启动所有的任务
     *
     * @throws SchedulerException
     */
    public void startJobs() throws SchedulerException {
        scheduler.start();
    }

    /**
     * shutdown所有的任务
     *
     * @throws SchedulerException
     */
    public void shutdownJobs() throws SchedulerException {
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

}

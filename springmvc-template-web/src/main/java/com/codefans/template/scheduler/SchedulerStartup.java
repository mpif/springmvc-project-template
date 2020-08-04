package com.codefans.template.scheduler;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @Author: codefans
 * @Date: 2020-08-03 23:10
 */

//@Component
public class SchedulerStartup {

//    @PostConstruct
    public void init() {

        try {
            JobDetailFactoryBean exampleJob = new JobDetailFactoryBean();
            exampleJob.setJobClass(SimpleScheduledJob.class);

            CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
            cronTrigger.setJobDetail(exampleJob.getObject());
            /**
             * 每隔5秒执行一次
             */
            cronTrigger.setCronExpression("*/5 * * * * ?");

            SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
            schedulerFactoryBean.setTriggers(cronTrigger.getObject());
            schedulerFactoryBean.start();
//
//            while(true) {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }



        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}

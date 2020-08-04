package com.codefans.template.scheduler;

import com.alibaba.fastjson.JSON;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @Author: codefans
 * @Date: 2020-08-04 8:36
 */

public class MyJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(MyJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("Validate Job Running task  thread:{} ",Thread.currentThread().getName());
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap dataMap = jobDetail.getJobDataMap();
        System.out.println("jobData:"+ JSON.toJSONString(dataMap));
        JobKey jobKey = jobDetail.getKey();
        System.out.println("jobName:"+jobKey.getName());
    }
}


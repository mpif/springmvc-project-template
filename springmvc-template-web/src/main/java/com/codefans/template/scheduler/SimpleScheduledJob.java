package com.codefans.template.scheduler;

import com.codefans.template.common.util.DateUtils;
import com.codefans.template.springextension.container.BeanPostProcessorExtension;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.ParseException;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2020-08-03 22:45
 */

public class SimpleScheduledJob extends QuartzJobBean {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(SimpleScheduledJob.class);


    @Override
    protected void executeInternal(org.quartz.JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("定时任务SimpleScheduledJob开始执行, 当前时间为:{}", DateUtils.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

/**
 * Copyright (C), 2015-2020, 京东
 * FileName: CustomApplicationListener
 * Author:   codefans
 * Date:     2020/8/4 14:24
 * Description: ApplicationListener实现类
 */
package com.codefans.template.springextension.container;


import com.codefans.template.web.util.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.*;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 *
 * ApplicationListener实现类
 *
 * @author codefans
 * @date 2020/08/04 14:24
 * @since 1.0.0
 */
public class CustomApplicationListener implements ApplicationListener<ApplicationEvent> {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(CustomApplicationListener.class);



    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ApplicationContextEvent) {
            this.process((ApplicationContextEvent) applicationEvent);
        } else if(applicationEvent instanceof PayloadApplicationEvent) {
            this.process((PayloadApplicationEvent) applicationEvent);
        } else if(applicationEvent instanceof RequestHandledEvent) {
            this.process((RequestHandledEvent)applicationEvent);
        } else if(applicationEvent instanceof ServletRequestHandledEvent) {
            this.process((ServletRequestHandledEvent) applicationEvent);
        } else if(applicationEvent instanceof ContextStartedEvent) {
            this.process((ContextStartedEvent) applicationEvent);
        } else if(applicationEvent instanceof ContextStoppedEvent) {
            this.process((ContextStoppedEvent) applicationEvent);
        } else if(applicationEvent instanceof ContextRefreshedEvent) {
            this.process((ContextRefreshedEvent) applicationEvent);
        } else if(applicationEvent instanceof ContextClosedEvent) {
            this.process((ContextClosedEvent) applicationEvent);
        }
    }

    /**
     * 处理ApplicationContextEvent
     * @param applicationContextEvent
     */
    public void process(ApplicationContextEvent applicationContextEvent) {
        log.info("-->CustomApplicationListener, applicationContextEvent, source={}", applicationContextEvent.getSource());
        ApplicationContextUtils.setApplicationContext(applicationContextEvent.getApplicationContext());


    }

    /**
     * 处理ContextStartedEvent
     * @param contextStartedEvent
     */
    public void process(ContextStartedEvent contextStartedEvent) {
        log.info("----------->CustomApplicationListener, contextStartedEvent, source={}", contextStartedEvent.getSource());
    }

    /**
     * 处理ContextRefreshedEvent
     * @param contextRefreshedEvent
     */
    public void process(ContextRefreshedEvent contextRefreshedEvent) {
        log.info("----------->CustomApplicationListener, contextRefreshedEvent, source={}", contextRefreshedEvent.getSource());
    }

    /**
     * 处理ContextClosedEvent
     * @param contextClosedEvent
     */
    public void process(ContextClosedEvent contextClosedEvent) {
        log.info("----------->CustomApplicationListener, contextClosedEvent, source={}", contextClosedEvent.getSource());
    }

    /**
     * 处理ServletRequestHandledEvent
     * @param servletRequestHandledEvent
     */
    public void process(ServletRequestHandledEvent servletRequestHandledEvent) {
        log.info("----------->CustomApplicationListener, servletRequestHandledEvent, source={}", servletRequestHandledEvent.getSource());
    }

    /**
     * 处理RequestHandledEvent
     * @param requestHandledEvent
     */
    public void process(RequestHandledEvent requestHandledEvent) {
        log.info("----------->CustomApplicationListener, requestHandledEvent, source={}", requestHandledEvent.getSource());
    }

    /**
     * 处理PayloadApplicationEvent
     * @param payloadApplicationEvent
     */
    public void process(PayloadApplicationEvent payloadApplicationEvent) {
        log.info("----------->CustomApplicationListener, payloadApplicationEvent, source={}", payloadApplicationEvent.getSource());
    }

}
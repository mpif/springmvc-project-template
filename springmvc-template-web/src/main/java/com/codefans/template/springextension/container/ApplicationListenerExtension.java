/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ApplicationListenerExtension
 * Author:   codefans
 * Date:     2020/7/23 17:15
 * Description: ApplicationListener扩展
 */
package com.codefans.template.springextension.container;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 *
 * ApplicationListener扩展
 *
 * @author codefans
 * @date 2020/07/23 17:15
 * @since 1.0.0
 */
@Component
public class ApplicationListenerExtension implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ApplicationContextEvent) {
            ApplicationContextEvent applicationContextEvent = (ApplicationContextEvent) applicationEvent;
            System.out.println("----------->ApplicationContextEvent," + applicationContextEvent.getSource());
        } else if(applicationEvent instanceof PayloadApplicationEvent) {
            PayloadApplicationEvent payloadApplicationEvent = (PayloadApplicationEvent) applicationEvent;
            System.out.println("PayloadApplicationEvent," + payloadApplicationEvent.getSource());
        } else if(applicationEvent instanceof RequestHandledEvent) {
            RequestHandledEvent requestHandledEvent = (RequestHandledEvent) applicationEvent;
            System.out.println("RequestHandledEvent," + requestHandledEvent.getSource());
        } else if(applicationEvent instanceof ServletRequestHandledEvent) {
            ServletRequestHandledEvent servletRequestHandledEvent = (ServletRequestHandledEvent) applicationEvent;
            System.out.println("ServletRequestHandledEvent," + servletRequestHandledEvent.getSource());
        } else if(applicationEvent instanceof ContextStartedEvent) {
            ContextStartedEvent contextStartedEvent = (ContextStartedEvent) applicationEvent;
            System.out.println("ContextStartedEvent," + contextStartedEvent.getSource());
        } else if(applicationEvent instanceof ContextStoppedEvent) {
            ContextStoppedEvent contextStoppedEvent = (ContextStoppedEvent) applicationEvent;
            System.out.println("ContextStoppedEvent," + contextStoppedEvent.getSource());
        } else if(applicationEvent instanceof ContextRefreshedEvent) {
            ContextRefreshedEvent contextRefreshedEvent = (ContextRefreshedEvent) applicationEvent;
            System.out.println("ContextRefreshedEvent," + contextRefreshedEvent.getSource());
        } else if(applicationEvent instanceof ContextClosedEvent) {
            ContextClosedEvent contextClosedEvent = (ContextClosedEvent) applicationEvent;
            System.out.println("ContextClosedEvent," + contextClosedEvent.getSource());
        }


    }





}
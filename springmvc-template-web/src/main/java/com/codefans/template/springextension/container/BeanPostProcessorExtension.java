package com.codefans.template.springextension.container;

import com.codefans.template.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2020-07-22 23:05
 */

public class BeanPostProcessorExtension implements BeanPostProcessor {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(BeanPostProcessorExtension.class);

    /**
     *
     */
    private boolean firstBefore = false;

    /**
     *
     */
    private boolean firstAfter = false;

    @Override
    public Object postProcessBeforeInitialization(Object obj, String beanName) throws BeansException {

        String applicationName = "";
        String displayName = "";
        try {
            if(!firstBefore) {
                log.info("BeanPostProcessorExtension.postProcessBeforeInitialization(), time={}", DateUtils.format(new Date()));
                firstBefore = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        try {
//            RequestAttributes requestAttr = RequestContextHolder.currentRequestAttributes();
//            if (!(requestAttr instanceof ServletRequestAttributes)) {
//                throw new IllegalStateException("Current request is not a servlet request");
//            }
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttr;
//            HttpServletRequest request = servletRequestAttributes.getRequest();
//            WebApplicationContext webApplicationContext = RequestContextUtils.findWebApplicationContext(request);
//            applicationName = webApplicationContext.getApplicationName();
//            displayName = webApplicationContext.getDisplayName();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        }

//        System.out.println("before initialization, beanName=" + beanName + ", applicationName=" + applicationName + ", displayName=" + displayName);

        return obj;
    }

    @Override
    public Object postProcessAfterInitialization(Object obj, String beanName) throws BeansException {

        String applicationName = "";
        String displayName = "";

        try {
            if(!firstAfter) {
                log.info("BeanPostProcessorExtension.postProcessAfterInitialization(), time={}", DateUtils.format(new Date()));
                firstAfter = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        try {
//            RequestAttributes requestAttr = RequestContextHolder.currentRequestAttributes();
//            if (!(requestAttr instanceof ServletRequestAttributes)) {
//                throw new IllegalStateException("Current request is not a servlet request");
//            }
//            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttr;
//            HttpServletRequest request = servletRequestAttributes.getRequest();
//            WebApplicationContext webApplicationContext = RequestContextUtils.findWebApplicationContext(request);
//            applicationName = webApplicationContext.getApplicationName();
//            displayName = webApplicationContext.getDisplayName();
//        } catch (IllegalStateException e) {
//            e.printStackTrace();
//        }

//        System.out.println("after initialization, beanName=" + beanName + ", applicationName=" + applicationName + ", displayName=" + displayName);

        return obj;
    }

}

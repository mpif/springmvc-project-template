package com.codefans.template.springextension.container;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: codefans
 * @Date: 2020-07-22 23:05
 */

public class BeanPostProcessorExtension implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object obj, String beanName) throws BeansException {

        String applicationName = "";
        String displayName = "";

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

        System.out.println("before initialization, beanName=" + beanName + ", applicationName=" + applicationName + ", displayName=" + displayName);

        return obj;
    }

    @Override
    public Object postProcessAfterInitialization(Object obj, String beanName) throws BeansException {

        String applicationName = "";
        String displayName = "";

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

        System.out.println("after initialization, beanName=" + beanName + ", applicationName=" + applicationName + ", displayName=" + displayName);

        return obj;
    }

}

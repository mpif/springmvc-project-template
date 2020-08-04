/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ApplicationContextAwareExtension
 * Author:   caishengzhi
 * Date:     2020/8/4 21:51
 * Description: ApplicationContextAware扩展
 */
package com.codefans.template.springextension.container;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 *
 * ApplicationContextAware扩展
 *
 * @author caishengzhi
 * @date 2020/08/04 21:51
 * @since 1.0.0
 */
@Component
public class ApplicationContextAwareExtension implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
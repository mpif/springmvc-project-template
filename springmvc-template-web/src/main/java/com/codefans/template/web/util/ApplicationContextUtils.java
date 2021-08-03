/**
 * Copyright (C), 2015-2020, 京东
 * FileName: ApplicationContextUtils
 * Author:   codefans
 * Date:     2020/8/4 14:55
 * Description: ApplicationContext工具类
 */
package com.codefans.template.web.util;


import org.springframework.context.ApplicationContext;

/**
 *
 * ApplicationContext工具类
 *
 * @author codefans
 * @date 2020/08/04 14:55
 * @since 1.0.0
 */
public class ApplicationContextUtils {
    private static ApplicationContext applicationContext;
    public static void setApplicationContext(ApplicationContext applicationContext){
        ApplicationContextUtils.applicationContext = applicationContext;
    }
}
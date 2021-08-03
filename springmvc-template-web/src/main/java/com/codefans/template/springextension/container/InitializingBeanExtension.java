/**
 * Copyright (C), 2015-2020, 京东
 * FileName: InitializingBeanExtension
 * Author:   codefans
 * Date:     2020/7/24 14:23
 * Description: InitializingBean扩展
 */
package com.codefans.template.springextension.container;


import com.codefans.template.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;

/**
 *
 * InitializingBean扩展
 *
 * @author codefans
 * @date 2020/07/24 14:23
 * @since 1.0.0
 */
public class InitializingBeanExtension implements InitializingBean {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(InitializingBeanExtension.class);


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBeanExtension.afterPropertiesSet(), time={}", DateUtils.format(new Date()));
    }



}
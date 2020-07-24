/**
 * Copyright (C), 2015-2020, 京东
 * FileName: FactoryBeanExtension
 * Author:   caishengzhi
 * Date:     2020/7/24 14:29
 * Description: FactoryBean扩展
 */
package com.codefans.template.springextension.container;


import com.codefans.template.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import java.util.Date;

/**
 *
 * FactoryBean扩展
 *
 * @author codefans
 * @date 2020/07/24 14:29
 * @since 1.0.0
 */
public class FactoryBeanExtension implements FactoryBean {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(FactoryBeanExtension.class);


    @Override
    public Object getObject() throws Exception {
        log.info("FactoryBeanExtension.getObject(), time={}", DateUtils.format(new Date()));
        return this;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
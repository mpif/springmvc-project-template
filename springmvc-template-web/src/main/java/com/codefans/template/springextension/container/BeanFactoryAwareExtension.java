/**
 * Copyright (C), 2015-2020, 京东
 * FileName: BeanFactoryAwareExtension
 * Author:   caishengzhi
 * Date:     2020/7/24 14:28
 * Description: BeanFactoryAware扩展
 */
package com.codefans.template.springextension.container;


import com.codefans.template.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import java.text.ParseException;
import java.util.Date;

/**
 *
 * BeanFactoryAware扩展
 *
 * @author codefans
 * @date 2020/07/24 14:28
 * @since 1.0.0
 */
public class BeanFactoryAwareExtension implements BeanFactoryAware {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(BeanFactoryAwareExtension.class);


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        try {
            log.info("BeanFactoryAwareExtension.setBeanFactory, time={}", DateUtils.format(new Date()));
        } catch (ParseException e) {
            log.error("日期解析异常", e);
        }
        BeanFactoryPostProcessorExtension beanFactoryPostProcessorExtension = (BeanFactoryPostProcessorExtension) beanFactory.getBean("beanFactoryPostProcessorExtension");
        if(beanFactoryPostProcessorExtension != null) {
            log.info("BeanFactoryPostProcessorExtension is not null!!!");
        }

    }


}
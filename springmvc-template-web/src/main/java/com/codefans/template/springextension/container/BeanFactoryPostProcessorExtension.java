/**
 * Copyright (C), 2015-2020, 京东
 * FileName: BeanFactoryPostProcessorExtension
 * Author:   codefans
 * Date:     2020/7/24 14:08
 * Description: BeanFactoryPostProcessor扩展
 */
package com.codefans.template.springextension.container;


import com.codefans.template.common.util.DateUtils;
import com.codefans.template.common.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

/**
 *
 * BeanFactoryPostProcessor扩展
 *
 * @author codefans
 * @date 2020/07/24 14:08
 * @since 1.0.0
 */
public class BeanFactoryPostProcessorExtension implements BeanFactoryPostProcessor {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(BeanFactoryPostProcessorExtension.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        try {
            log.info("BeanFactoryPostProcessorExtension.postProcessBeanFactory(), time=" + DateUtils.format(new Date()));
        } catch (ParseException e) {
            log.error("日期解析异常", e);
        }
        this.printAllBean(configurableListableBeanFactory);

    }

    private void printAllBean(ConfigurableListableBeanFactory configurableListableBeanFactory) {
        Iterator<String> iter = configurableListableBeanFactory.getBeanNamesIterator();
        int totalBeans = 0;
        String beanName = "";
        Object obj = null;
        while(iter.hasNext()) {
            beanName = iter.next();
            if("beanFactoryPostProcessorExtension".equals(beanName)) {
                log.info("ignore BeanFactoryPostProcessorExtension................");
                continue;
            }
//            obj = configurableListableBeanFactory.getBean(beanName);
//            log.info("beanName={}, obj={}", beanName, obj);
            totalBeans++;
        }
        log.info("total={}", totalBeans);
    }



}
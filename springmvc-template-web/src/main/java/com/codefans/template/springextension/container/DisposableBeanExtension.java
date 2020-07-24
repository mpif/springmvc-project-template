/**
 * Copyright (C), 2015-2020, 京东
 * FileName: DisposibleBeanExtension
 * Author:   caishengzhi
 * Date:     2020/7/24 14:24
 * Description: DisposibleBean扩展
 */
package com.codefans.template.springextension.container;


import com.codefans.template.common.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.util.Date;

/**
 *
 * DisposibleBean扩展
 *
 * @author caishengzhi
 * @date 2020/07/24 14:24
 * @since 1.0.0
 */
public class DisposableBeanExtension implements DisposableBean {

    /**
     *
     */
    private Logger log = LoggerFactory.getLogger(DisposableBeanExtension.class);


    @Override
    public void destroy() throws Exception {
        log.info("DisposableBeanExtension.destroy(), time={}", DateUtils.format(new Date()));
    }


}
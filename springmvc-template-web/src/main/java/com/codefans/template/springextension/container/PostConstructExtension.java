/**
 * Copyright (C), 2015-2020, 京东
 * FileName: PostConstructExtension
 * Author:   caishengzhi
 * Date:     2020/7/30 14:58
 * Description: PostConstruct扩展
 */
package com.codefans.template.springextension.container;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *
 * PostConstruct扩展
 *
 * @author caishengzhi
 * @date 2020/07/30 14:58
 * @since 1.0.0
 */
public class PostConstructExtension {

//    @Autowired
    @Resource
    private ApplicationContext applicationContext;

    public PostConstructExtension() {
        System.out.println("PostConstructExtension() execute...........");
        if(applicationContext != null) {
            System.out.println("applicationContext is not null, in construction");

        }
    }

    @PostConstruct
    public void init() {
        System.out.println("PostConstructExtension.init().............");
        if(applicationContext != null) {
            System.out.println("applicationContext is not null, in init");

        }
    }

}
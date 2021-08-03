/**
 * Copyright (C), 2015-2021, 京东
 * FileName: SingletonService2
 * Author:   caishengzhi
 * Date:     2021/8/3 18:21
 * Description:
 */
package com.codefans.template.circlereference.singleton;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 * @author: codefans
 * @Date: 2021/08/03 18:21
 * @since: 1.0.0
 */
@Service
public class SingletonFieldInjectService2 {

    @Autowired
    private SingletonFieldInjectService1 singletonService1;

    public void print() {
        System.out.println("SingletonService2.print()....");
    }
}
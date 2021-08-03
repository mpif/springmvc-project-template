/**
 * Copyright (C), 2015-2021, 京东
 * FileName: ProxyFieldInjectService1
 * Author:   caishengzhi
 * Date:     2021/8/3 20:10
 * Description:
 */
package com.codefans.template.circlereference.singleton;


import com.codefans.template.circlereference.prototype.PrototypeFieldInjectService1;
import com.codefans.template.circlereference.prototype.PrototypeFieldInjectService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 * @author: codefans
 * @Date: 2021/08/03 20:10
 * @since: 1.0.0
 */
@Service
public class ProxyFieldInjectService1 {

    @Autowired
    private ProxyFieldInjectService2 proxyFieldInjectService2;

    @Async
    public void test1() {

    }

}
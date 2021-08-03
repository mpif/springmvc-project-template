/**
 * Copyright (C), 2015-2021, 京东
 * FileName: PrototypeFieldInjectService1
 * Author:   caishengzhi
 * Date:     2021/8/3 19:51
 * Description:
 */
package com.codefans.template.circlereference.prototype;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 *
 *
 * @author: codefans
 * @Date: 2021/08/03 19:51
 * @since: 1.0.0
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class PrototypeFieldInjectService1 {

    @Autowired
    private PrototypeFieldInjectService2 PrototypeFieldInjectService2;

    public void test1() {
    }

}
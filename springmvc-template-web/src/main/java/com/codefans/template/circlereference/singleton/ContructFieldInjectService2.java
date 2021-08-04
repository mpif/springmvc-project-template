package com.codefans.template.circlereference.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: codefans
 * @Date: 2021-08-04 7:03
 */
@Service
public class ContructFieldInjectService2 {

    @Autowired
    private ContructFieldInjectService1 contructFieldInjectService1;

//    public ContructFieldInjectService2(ContructFieldInjectService1 contructFieldInjectService1) {
//        this.contructFieldInjectService1 = contructFieldInjectService1;
//    }
    public ContructFieldInjectService2() {
        System.out.println("contructFieldInjectService2");
    }
}

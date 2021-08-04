package com.codefans.template.circlereference.singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: codefans
 * @Date: 2021-08-04 7:03
 */
@Service
public class ContructFieldInjectService1 {

//    @Autowired
    private ContructFieldInjectService2 contructFieldInjectService2;

//    public ContructFieldInjectService1(ContructFieldInjectService2 contructFieldInjectService2) {
//        this.contructFieldInjectService2 = contructFieldInjectService2;
//        System.out.println("contructFieldInjectService1");
//    }

}

/**
 * Copyright (C), 2015-2021, 京东
 * FileName: SingletonContructInjectService2
 * Author:   caishengzhi
 * Date:     2021/8/3 19:47
 * Description:
 */
package com.codefans.template.circlereference.singleton;


import org.springframework.stereotype.Service;

/**
 * 构造方法注释打开,就是属于:
 * 单例的构造函数注入, 会发生循环依赖问题
 * 这种情况, 启动会报错。
 *
 * @author: codefans
 * @Date: 2021/08/03 19:47
 * @since: 1.0.0
 */
@Service
public class SingletonContructInjectService2 {

    private SingletonContructInjectService1 singletonContructInjectService1;

//    public SingletonContructInjectService2(SingletonContructInjectService1 singletonContructInjectService1) {
//        this.singletonContructInjectService1 = singletonContructInjectService1;
//    }

}
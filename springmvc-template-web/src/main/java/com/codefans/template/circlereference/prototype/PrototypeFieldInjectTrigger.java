/**
 * Copyright (C), 2015-2021, 京东
 * FileName: PrototypeFieldInjectTrigger
 * Author:   caishengzhi
 * Date:     2021/8/3 20:01
 * Description:
 */
package com.codefans.template.circlereference.prototype;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * PrototypeFieldInjectService1和PrototypeFieldInjectService2属于多例的setter注入循环依赖,
 * 但是可以启动, 只有在单例中注入PrototypeFieldInjectService1, 就会触发PrototypeFieldInjectService1和PrototypeFieldInjectService2的提前初始化，就会发生循环依赖。
 *
 * @author: codefans
 * @Date: 2021/08/03 20:01
 * @since: 1.0.0
 */
@Service
public class PrototypeFieldInjectTrigger {

//    @Autowired
//    private PrototypeFieldInjectService1 prototypeFieldInjectService1;

}
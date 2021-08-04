package com.codefans.template.circlereference.dependson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

/**
 * 把注释去掉, 就会发生循环依赖
 * @Author: codefans
 * @Date: 2021-08-04 6:51
 */
//@DependsOn(value = "dependsOnService1")
@Service
public class DependsOnService2 {

    @Autowired
    private DependsOnService1 dependsOnService1;

}

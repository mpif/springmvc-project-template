/**
 * Copyright (C), 2015-2020, 京东
 * FileName: StaticInit
 * Author:   caishengzhi
 * Date:     2020/8/3 11:33
 * Description: 静态初始化
 */
package com.codefans.template.init;


import com.codefans.template.common.util.DateUtils;

import java.util.Date;

/**
 *
 * 静态初始化
 *
 * @author caishengzhi
 * @date 2020/08/03 11:33
 * @since 1.0.0
 */
public class StaticInit {

    static {

        try {
            System.out.println("StaticInit ........ " + DateUtils.format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
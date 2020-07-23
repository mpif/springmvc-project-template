package com.codefans.template.common.util;

/**
 * @Author: codefans
 * @Date: 2018-07-07 23:55
 */

public class StringUtils {

    public static boolean isEmpty(String str) {
        if(str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

}

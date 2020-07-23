package com.codefans.template.service.impl;

import com.codefans.template.service.WxService;
import org.springframework.stereotype.Service;

/**
 * @Author: codefans
 * @Date: 2020-07-21 23:33
 */
@Service
public class WxServiceImpl implements WxService {
    @Override
    public String queryWxNo() {
        return "WxNo_Is_110";
    }
}

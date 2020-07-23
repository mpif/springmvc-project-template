package com.codefans.template.web.controller;

import com.alibaba.fastjson.JSON;
import com.codefans.template.common.data.Code;
import com.codefans.template.common.data.Message;
import com.codefans.template.common.data.Messages;
import com.codefans.template.common.util.ValidateUtil;
import com.codefans.template.service.WxService;
import com.codefans.template.springextension.xml.config.AppConfig;
import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: codefans
 * @Date: 2018-07-08 0:58
 */
@Controller
@RequestMapping("templateCtl")
public class TemplateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateController.class);

    @Resource
    private WxService wxService;

    @Resource
    private AppConfig appConfig;

    @RequestMapping(method= {RequestMethod.GET,RequestMethod.POST}, value="/index")
    @ResponseBody
    public Message<String> index(
            @RequestParam(value = "xmlParam", required = false) String xmlParam) {

        Message<String> message = null;
        try {
            if (!ValidateUtil.isNotEmpty(xmlParam)) {
                LOGGER.error("参数校验失败xmlParam={}", xmlParam);
                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
            }

            wxService.queryWxNo();



            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("xmlParam", xmlParam);
            dataMap.put("message", "welcome to springmvc testing program");
            dataMap.put("appConfig", appConfig.toString());

            message = Messages.success(JSON.toJSONString(dataMap));

            return message;
        } catch (Exception e) {
            LOGGER.info("回调系统异常.异常信息：{}", Throwables.getStackTraceAsString(e));
            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "回调系统异常");
        }
    }


    @RequestMapping(method= {RequestMethod.GET,RequestMethod.POST}, value="/callback/{version}/{appkey}")
    @ResponseBody
    public Message<String> callback(
            @PathVariable(value = "version") String version,
            @PathVariable(value = "appkey") String appkey,
            @RequestParam(value = "xmlParam") String xmlParam) {

        Message<String> message = null;
        try {
            if (!ValidateUtil.isNotEmpty(version,appkey,xmlParam)) {
                LOGGER.error("参数校验失败version={},appkey={},xmlParam={}", version, appkey, xmlParam);
                return Messages.failed(Code.PARAMATERSISNULL.getValue(), "参数校验失败");
            }

            Map<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("version", version);
            dataMap.put("appkey", appkey);
            dataMap.put("xmlParam", xmlParam);
            dataMap.put("message", "welcome to springmvc testing program");

            message = Messages.success(JSON.toJSONString(dataMap));

            return message;
        } catch (Exception e) {
            LOGGER.info("回调系统异常.异常信息：{}", Throwables.getStackTraceAsString(e));
            return Messages.failed(Code.SYSTEMEXCEPTION.getValue(), "回调系统异常");
        }
    }

}

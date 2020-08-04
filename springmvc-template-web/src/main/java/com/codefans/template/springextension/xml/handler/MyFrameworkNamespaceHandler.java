package com.codefans.template.springextension.xml.handler;

import com.codefans.template.springextension.xml.parser.MyFrameworkBeanDefinitionParser;
import com.codefans.template.springextension.xml.util.VersionUtils;
import com.codefans.template.springextension.xml.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author: codefans
 * @date: 2019-08-20 13:53
 */
public class MyFrameworkNamespaceHandler extends NamespaceHandlerSupport {

    private Logger log = LoggerFactory.getLogger(MyFrameworkNamespaceHandler.class);

    static {
        VersionUtils.checkDuplicate(MyFrameworkNamespaceHandler.class);
    }

    public void init() {
        log.info("MyFrameworkNamespaceHandler.init() method called.....");
        registerBeanDefinitionParser("app", new MyFrameworkBeanDefinitionParser(AppConfig.class, true));
    }

}

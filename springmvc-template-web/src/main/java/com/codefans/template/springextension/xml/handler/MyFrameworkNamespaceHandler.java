package com.codefans.template.springextension.xml.handler;

import com.codefans.template.springextension.xml.parser.MyFrameworkBeanDefinitionParser;
import com.codefans.template.springextension.xml.util.VersionUtils;
import com.codefans.template.springextension.xml.config.AppConfig;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author: codefans
 * @date: 2019-08-20 13:53
 */
public class MyFrameworkNamespaceHandler extends NamespaceHandlerSupport {

    static {
        VersionUtils.checkDuplicate(MyFrameworkNamespaceHandler.class);
    }

    public void init() {
        registerBeanDefinitionParser("app", new MyFrameworkBeanDefinitionParser(AppConfig.class, true));
    }

}

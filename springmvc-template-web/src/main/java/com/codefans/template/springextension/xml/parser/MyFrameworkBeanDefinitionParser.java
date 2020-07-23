package com.codefans.template.springextension.xml.parser;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author: codefans
 * @date: 2019-08-20 14:36
 */
public class MyFrameworkBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    private final boolean required;

    public MyFrameworkBeanDefinitionParser(Class<?> beanClass, boolean required) {
        this.beanClass = beanClass;
        this.required = required;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return parse(element, parserContext, beanClass, required);
    }

    public BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass, boolean required) {

        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        String id = element.getAttribute("id");
        String name = element.getAttribute("name");
        if ((id == null || id.length() == 0) && required) {
            String beanName = "";
            if (name == null || name.length() == 0) {
                beanName = beanClass.getName();
            } else {
                beanName = name;
            }
//            beanName = beanClass.getName();
            id = beanName;
            int counter = 2;
            while (parserContext.getRegistry().containsBeanDefinition(id)) {
                id = beanName + (counter++);
            }
        }
        if (id != null && id.length() > 0) {
            if (parserContext.getRegistry().containsBeanDefinition(id))  {
                throw new IllegalStateException("Duplicate spring bean id " + id);
            }
            /**
             * 只有注册之后，相应的bean才会被初始化
             */
            parserContext.getRegistry().registerBeanDefinition(id, beanDefinition);
            /**
             * 为bean的属性赋值
             */
            beanDefinition.getPropertyValues().addPropertyValue("id", id);
        }


        String password = element.getAttribute("password");
        String ip = element.getAttribute("ip");
        String isDefault = element.getAttribute("isDefault");
        /**
         * 为bean的name属性赋值
         */
        beanDefinition.getPropertyValues().addPropertyValue("name", name);
        beanDefinition.getPropertyValues().addPropertyValue("password", password);
        beanDefinition.getPropertyValues().addPropertyValue("ip", ip);
        beanDefinition.getPropertyValues().addPropertyValue("isDefault", isDefault);

        String requiredStr = element.getAttribute("required");
        if(requiredStr != null && requiredStr.trim().length() > 0) {
            boolean isRequired = Boolean.parseBoolean(requiredStr);
            beanDefinition.getPropertyValues().addPropertyValue("required", isRequired);
        }
        String ageAttr = element.getAttribute("age");
        if(ageAttr != null && ageAttr.trim().length() > 0) {
            int age = Integer.parseInt(element.getAttribute("age"));
            beanDefinition.getPropertyValues().addPropertyValue("age", age);
        }
        String moneyAttr = element.getAttribute("money");
        if(moneyAttr != null && moneyAttr.trim().length() > 0) {
            long money = Integer.parseInt(moneyAttr);
            beanDefinition.getPropertyValues().addPropertyValue("money", money);
        }

        return beanDefinition;
    }


}

package com.codefans.template.springextension.xml.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import java.io.Serializable;

/**
 * @author: codefans
 * @date: 2019-08-20 14:31
 */
public class AppConfig implements InitializingBean, DisposableBean, ApplicationContextAware, ApplicationListener, BeanNameAware, Serializable {

    private Logger log = LoggerFactory.getLogger(AppConfig.class);

    private String beanName;

    private String id;

    private String name;

    private String password;

    private boolean required;

    private int age;

    private long money;

    private String ip;

    private String isDefault;

    private ApplicationContext applicationContext;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        System.out.println("AppConfig.java --> setApplicationContext method invoke.");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("AppConfig.java --> setBeanName method invoke.");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("AppConfig.java --> destroy method invoke.");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("AppConfig.java --> afterPropertiesSet method invoke.");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (ContextRefreshedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            System.out.println("context refresh!");
            log.info("spring application context initialize success.");
        } else if(ContextStartedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            System.out.println("context started!");
            log.info("spring application context initialize success.");
        } else if(ContextStoppedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            System.out.println("context stopped!");
            log.info("spring application context initialize success.");
        } else if(ContextClosedEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            System.out.println("context closed!");
            log.info("spring application context initialize success.");
        } else if(ServletRequestHandledEvent.class.getName().equals(applicationEvent.getClass().getName())) {
            System.out.println("AppConfig.onApplicationEvent, ServletRequestHandledEvent !");
            log.info("AppConfig.onApplicationEvent, ServletRequestHandledEvent .");
        }
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "beanName='" + beanName + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", required=" + required +
                ", age=" + age +
                ", money=" + money +
                ", ip='" + ip + '\'' +
                ", isDefault='" + isDefault + '\'' +
                ", applicationContext=" + applicationContext +
                '}';
    }
}

package com.yczuoxin.demo.bean.domian;

import com.yczuoxin.demo.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class UserHolder implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private final User user;

    private String desc;

    private String beanName;

    private BeanFactory beanFactory;

    private ClassLoader classLoader;

    private Environment environment;

    public UserHolder(User user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @PostConstruct
    public void initUserHolder(){
        this.desc = "this is recovered user holder V3";
        System.out.println("initUserHolder update user desc......" + this.desc);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.desc = "this is recovered user holder V4";
        System.out.println("afterPropertiesSet update user desc......" + this.desc);
    }

    public void init() {
        this.desc = "this is recovered user holder V5";
        System.out.println("init update user desc......" + this.desc);
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.desc = "this is recovered user holder V7";
        System.out.println("afterSingletonsInstantiated update user desc......" + this.desc);
    }

    @PreDestroy
    public void preDestroy(){
        this.desc = "this is recovered user holder V9";
        System.out.println("preDestroy update user desc......" + this.desc);
    }

    @Override
    public void destroy() throws Exception {
        this.desc = "this is recovered user holder V10";
        System.out.println("destroy update user desc......" + this.desc);
    }

    public void doDestroy() {
        this.desc = "this is recovered user holder V11";
        System.out.println("doDestroy update user desc......" + this.desc);
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", desc='" + desc + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}

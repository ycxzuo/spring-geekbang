package com.yczuoxin.demo.bean.factory;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class DefaultUserFactory implements UserFactory, InitializingBean {

    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct 初始化中。。。");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet 初始化中。。。");
    }

    public void initUserFactory(){
        System.out.println("initUserFactory 初始化中。。。");
    }
}

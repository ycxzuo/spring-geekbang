package com.yczuoxin.demo.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    public volatile boolean flag = false;

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

    @PreDestroy
    public void PreDestroy() {
        System.out.println("@PreDestroy 销毁中。。。");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy 销毁中。。。");
    }

    public void destroyMethod(){
        System.out.println("destroyMethod 销毁中。。。");
    }

    @Override
    public void finalize() throws Throwable {
        flag = true;
        System.out.println("DefaultUserFactory 正在被 GC 回收");
    }
}

package com.yczuoxin.demo.domain;


import org.springframework.beans.factory.BeanNameAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

public class User implements BeanNameAware {

    private Long id;

    private String name;

    private Company company;

    private Properties context;

    private Address homeAddress;

    private String addressStr;

    private transient String beanName;

    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("zx");
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getAddressStr() {
        return addressStr;
    }

    public void setAddressStr(String addressStr) {
        this.addressStr = addressStr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", context=" + context +
                ", homeAddress=" + homeAddress +
                ", addressStr='" + addressStr + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("User Bean [" + beanName + "] 初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean [" + beanName + "] 销毁中...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}

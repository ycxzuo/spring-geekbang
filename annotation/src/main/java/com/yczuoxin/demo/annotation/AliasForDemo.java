package com.yczuoxin.demo.annotation;

import org.springframework.core.annotation.AnnotationUtils;

@MyComponentScan2(value2 = "aaa", value1 = "bbb")
public class AliasForDemo {
    public static void main(String[] args) {
        MyComponentScan2 annotation = AliasForDemo.class.getAnnotation(MyComponentScan2.class);
        System.out.println(annotation.value1());
        System.out.println(annotation.value2());

        // 报错，因为 AnnotationUtils.findAnnotation 方法返回是一个经过别名处理后的注解
        // 本质原理就是使用了 AOP 来对注解对象做了次动态代理，而用于处理代理的对象为 SynthesizedAnnotationInvocationHandler
        MyComponentScan2 annotation2 = AnnotationUtils.findAnnotation(AliasForDemo.class, MyComponentScan2.class);
        System.out.println(annotation2.value1());
        System.out.println(annotation2.value2());
    }
}

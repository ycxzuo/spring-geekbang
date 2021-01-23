package com.yczuoxin.demo;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;

/**
 * {@link MethodParameter} 示例
 */
public class MethodParameterDemo {

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = MethodParameterDemo.class.getMethod("test", int.class);
        MethodParameter methodParameter = MethodParameter.forMethodOrConstructor(method, 0);
        methodParameter.initParameterNameDiscovery(new DefaultParameterNameDiscoverer());
        System.out.println(methodParameter.getParameterType());
        System.out.println(methodParameter.getParameterName());
    }

    public String test(int s1) {
        return null;
    }
}

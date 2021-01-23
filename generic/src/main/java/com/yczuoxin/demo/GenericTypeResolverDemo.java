package com.yczuoxin.demo;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        display("getString", Comparable.class, GenericTypeResolverDemo.class);
        display("getDateList", List.class, GenericTypeResolverDemo.class);
        display("getList", List.class, GenericTypeResolverDemo.class);
        display("getStringList", List.class, GenericTypeResolverDemo.class);

        System.out.println("---------------------------");
        Method method = ArrayList.class.getMethod("get", int.class);
        System.out.println(GenericTypeResolver.resolveReturnType(method, ArrayList.class));
        System.out.println(GenericTypeResolver.resolveReturnType(method, StringList.class));
        // 拿到类以及其父类所有的泛型所对应的类
        System.out.println(GenericTypeResolver.getTypeVariableMap(StringList.class));
    }

    public static void display(String methodName, Class<?> genericIfc, Class<?> declaredClass) throws NoSuchMethodException {
        Method method = GenericTypeResolverDemo.class.getMethod(methodName);
        // 第二个参数是该方法被使用的类，比如 get() 方法在 ArrayList 中就是 Object，在 StringList 就被具体化变成了 String
        Class<?> returnType = GenericTypeResolver.resolveReturnType(method, declaredClass);
        System.out.printf("GenericTypeResolver.resolveReturnType(%s, %s) = %s\n", methodName, genericIfc.getSimpleName(), returnType);

        // 只能输出泛型具体化的类型
        // 第二个参数是需要解析的返回值类型的泛型接口或者父类的泛型，如果没有具体化就是 null
        Class<?> returnTypeArgument = GenericTypeResolver.resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s, %s) = %s\n", methodName, genericIfc.getSimpleName(), returnTypeArgument);
    }

    public String getString(){
        return null;
    }

    public <E> List<E> getList(){
        return null;
    }

    public List<Date> getDateList(){
        return null;
    }

    public StringList getStringList() {
        return null;
    }
}

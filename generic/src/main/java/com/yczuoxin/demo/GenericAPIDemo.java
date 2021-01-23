package com.yczuoxin.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

public class GenericAPIDemo {
    public static void main(String[] args) {
        // 原生类型
        Class primitiveClass = int.class;
        // 数组类型
        Class arrayClass = long[].class;
        // 原始类型
        Class rawType = String.class;
        // 参数化类型
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println("parameterizedType: " + parameterizedType);

        //
        Type[] type = parameterizedType.getActualTypeArguments();
        Stream.of(type).map(TypeVariable.class::cast).forEach(System.out::println);
    }
}

package com.yczuoxin.demo;

import org.springframework.core.ResolvableType;

/**
 * {@link ResolvableType} 示例
 */
public class ResolvableTypeDemo {

    public static void main(String[] args) {
        ResolvableType stringResolvableType = ResolvableType.forClass(StringList.class);
        System.out.println(stringResolvableType.getGeneric(0)); // ?(ResolvableType.NONE) 因为 String 没有泛型，但是不会返回 null

        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        // getSuperType 获取父类的 Raw Type
        System.out.println(resolvableType.getSuperType().resolve()); // class java.util.ArrayList
        // getSuperType 获取父类的 ParameterizedType
        System.out.println(resolvableType.getSuperType().resolveGeneric(0)); // class java.lang.String
        // resolve 获取的是 Raw Type
        System.out.println(resolvableType.resolve()); // class com.yczuoxin.demo.StringList
        // getGeneric 获取的是 ParameterizedType
        System.out.println(resolvableType.getGeneric(0)); // ?(ResolvableType.NONE) 因为 StringList 没有泛型，但是不会返回 null

        ResolvableType collection = resolvableType.asCollection(); // 把类型转换成 Collection，泛型又会带回来
        // 转换成为了 java.util.Collection<String>
        System.out.println(collection.resolve()); // interface java.util.Collection
        System.out.println(collection.resolveGeneric(0)); // String
    }

}

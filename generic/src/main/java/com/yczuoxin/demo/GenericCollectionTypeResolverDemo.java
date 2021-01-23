package com.yczuoxin.demo;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * {@link GenericCollectionTypeResolver} 示例
 */
public class GenericCollectionTypeResolverDemo {

    private StringList stringList;
    private ArrayList<String> strings;
    private ArrayList arrayList;

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<?> stringListType = GenericCollectionTypeResolver.getCollectionType(StringList.class);
        System.out.println(stringListType);

        Class<?> arrayListType = GenericCollectionTypeResolver.getCollectionType(ArrayList.class);
        System.out.println(arrayListType);

        Field stringListField = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(stringListField));

        Field stringsField = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(stringsField));

        Field arrayListField = GenericCollectionTypeResolverDemo.class.getDeclaredField("arrayList");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(arrayListField));
    }

}

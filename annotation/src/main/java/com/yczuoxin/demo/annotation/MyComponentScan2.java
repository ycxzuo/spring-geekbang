package com.yczuoxin.demo.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(value = "scanBasePackages", annotation = MyComponentScan.class) // 传递和覆盖
    String[] basePackages() default {};

    @AliasFor(value = "packages", annotation = MyComponentScan.class) // 覆盖
    String[] packages() default {};

    @AliasFor("value1") // 显示别名
    String value2() default "";

    @AliasFor("value2") // 显示别名
    String value1() default "";

}
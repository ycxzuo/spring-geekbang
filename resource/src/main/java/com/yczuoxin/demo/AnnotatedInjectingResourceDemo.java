package com.yczuoxin.demo;

import com.yczuoxin.demo.utils.ResourceUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.stream.Stream;

public class AnnotatedInjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultProperties;

    // 此处要用 classpath*，不然的话注入不进来匹配的文件
    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResources;

//    此处解析到 Collection 中的是没有经过 * 匹配的原始路径的资源，所以导致读取失败
//    @Value("classpath*:/META-INF/*.properties")
//    private Collection<Resource> propertiesResourceCollection;


    @PostConstruct
    public void init() {
        System.out.println(ResourceUtil.printResource(defaultProperties));
        System.out.println("---------------------------");
        Stream.of(propertiesResources).map(ResourceUtil::printResource).forEach(System.out::println);
//        propertiesResourceCollection.stream().map(ResourceUtil::printResource).forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotatedInjectingResourceDemo.class);
        applicationContext.refresh();

        applicationContext.close();
    }

}

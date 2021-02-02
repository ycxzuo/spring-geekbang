package com.yczuoxin.demo.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld(){
        return "Hello,World";
    }

}

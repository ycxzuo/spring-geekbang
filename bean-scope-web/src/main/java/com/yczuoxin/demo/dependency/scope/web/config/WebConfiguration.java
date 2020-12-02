package com.yczuoxin.demo.dependency.scope.web.config;

import com.yczuoxin.demo.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
    @RequestScope
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("yczuoxin");
        return user;
    }

}

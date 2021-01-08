package com.yczuoxin.demo;

import com.yczuoxin.demo.entity.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Locale;

public class ErrorsMessageDemo {

    public static void main(String[] args) {
        User user = new User();
        user.setName("yczuoxin");

        Errors errors = new BeanPropertyBindingResult(user, "user");

        errors.reject("user.properties.not.null");

        errors.rejectValue("name", "name.required");

        FieldError fieldError = errors.getFieldError();

        ObjectError globalError = errors.getGlobalError();

        MessageSource messageSource = createMessageSource();

        errors.getAllErrors().forEach(error -> System.out.println(messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault())));
    }

    protected static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties.not.null", Locale.getDefault(), "User 对象不能为空！");
        messageSource.addMessage("id.required", Locale.getDefault(), "user id must not be null.");
        messageSource.addMessage("name.required", Locale.getDefault(), "user name must not be null.");
        messageSource.addMessage("name.length", Locale.getDefault(), "用户的名称不能少于 5 个字符");
        return messageSource;
    }
}

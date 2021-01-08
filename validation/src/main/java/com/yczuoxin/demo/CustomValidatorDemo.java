package com.yczuoxin.demo;

import com.yczuoxin.demo.entity.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Locale;

/**
 * Spring 自定义的 {@link Validator} 示例
 */
public class CustomValidatorDemo {
    public static void main(String[] args) {
        // 创建需要校验的对象
        User user = new User();
        user.setName("yczuo");
        // 创建校验器
        Validator validator = new UserValidator();
        // 是否校验器支持校验改对象
        System.out.println("UserValidator 是否成功校验对象 user :" + validator.supports(user.getClass()));
        // 创建 Errors
        Errors errors = new BeanPropertyBindingResult(user, "user");
        // 进行校验
        validator.validate(user, errors);
        // 创建 MessageSource 用于输出错误文案
        MessageSource messageSource = ErrorsMessageDemo.createMessageSource();
        // 输出校验结果
        errors.getAllErrors().forEach(error -> System.out.println(messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault())));
    }

    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
            String name = user.getName();
            if (name.length() < 5) {
                errors.rejectValue("name", "name.length");
            }
        }
    }
}

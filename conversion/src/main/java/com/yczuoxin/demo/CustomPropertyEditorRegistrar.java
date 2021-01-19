package com.yczuoxin.demo;

import com.yczuoxin.demo.domain.Address;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Properties;

/**
 * 注册自定义的属性转换
 * 第一个参数是需要转换的类型，
 * 第二个参数是可选参数，是属性的名称，如果不填就是通用的
 * 第三个参数是注册的 PropertyEditor 实现
 *
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        registry.registerCustomEditor(Properties.class, "context", new String2PropertiesPropertyEditor());
        registry.registerCustomEditor(Address.class,
                // "homeAddress",
                new String2AddressPropertyEditor());
    }
}

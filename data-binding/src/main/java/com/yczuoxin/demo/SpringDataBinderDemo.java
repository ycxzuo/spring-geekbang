package com.yczuoxin.demo;

import com.yczuoxin.demo.domain.Company;
import com.yczuoxin.demo.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

public class SpringDataBinderDemo {
    public static void main(String[] args) {
        User user = new User();
        // 创建 DataBinder
        DataBinder dataBinder = new DataBinder(user, "user");
        // 设置 PropertyValues 的值
        Map<String, Object> source = new HashMap<>();
        source.put("id", 10L);
        source.put("name", "ycxzuo");
        // 设置 Bean 中不存在的属性
        // 结论：默认跳过
        source.put("age", 20);
        // 设置嵌套属性
        // 结论：可以创建嵌套对象，并进行赋值
        source.put("company", new Company());
        source.put("company.name", "test");

        // 创建 propertyValues
        PropertyValues propertyValues = new MutablePropertyValues(source);

        // 设置是否忽略未知字段，默认是 true
        // dataBinder.setIgnoreUnknownFields(false);

        // 设置是否允许自动创建嵌套对象
        dataBinder.setAutoGrowNestedPaths(false);

        // 设置是否忽略非法字段，默认是 false，改为 true，该值要和 setAutoGrowNestedPaths 一起使用，就会忽略报错，但是不会设置嵌套对象的值
        //dataBinder.setIgnoreInvalidFields(true);

        // 上面的都会强校验，如果不成功就会抛出异常

        // 设置设置属性白名单，设置白名单就只会设置白名单中的属性值
        //dataBinder.setAllowedFields("id", "name");

        // 设置设置属性黑名单，设置黑名单就不会设置黑名单中的属性值，即使是在白名单中的属性
        dataBinder.setDisallowedFields("id");

        // 下面的都是软校验，只会在 result 上体现结果

        // 设置必须存在的字段
        dataBinder.setRequiredFields("id");

        dataBinder.bind(propertyValues);

        System.out.println(user);

        System.out.println(dataBinder.getBindingResult());
    }
}

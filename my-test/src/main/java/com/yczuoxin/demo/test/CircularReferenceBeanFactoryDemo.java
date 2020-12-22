package com.yczuoxin.demo.test;

import com.yczuoxin.demo.domain.Student;
import com.yczuoxin.demo.domain.Teacher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class CircularReferenceBeanFactoryDemo {

    @Bean
    public static Student student() {
        Student student = new Student();
        student.setId(2L);
        student.setName("张三");
        return student;
    }

    @Bean
    public static Teacher teacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("李四");
        return teacher;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(CircularReferenceBeanFactoryDemo.class);
        context.refresh();
        Student student = context.getBean("student", Student.class);
        Teacher teacher = context.getBean("teacher", Teacher.class);
        System.out.println(student);
        System.out.println(teacher);
        context.close();
    }
}

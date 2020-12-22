package com.yczuoxin.demo.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {

    private Long id;

    private String name;

    @Autowired
    private Teacher teacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher.name=" + teacher.getName() +
                '}';
    }
}

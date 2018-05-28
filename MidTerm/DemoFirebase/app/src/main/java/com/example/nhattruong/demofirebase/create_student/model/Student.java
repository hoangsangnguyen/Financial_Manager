package com.example.nhattruong.demofirebase.create_student.model;

import java.io.Serializable;

/**
 * Created by nhattruong on 4/15/2018.
 */

public class Student implements Serializable {
    private String name;
    private String studentCode;

    public Student() {
    }

    public Student(String name, String studentCode) {
        this.name = name;
        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}

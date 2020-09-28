package com.prc.springbootswagger3.common;

import com.prc.springbootswagger3.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static List<Student> intiDataList() {
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(1, "张三", 21);
        Student student2 = new Student(2, "李四", 15);
        Student student3 = new Student(3, "王二狗", 38);
        Student student4 = new Student(4, "赵六", 51);
        Student student5 = new Student(5, "镜", 23);
        Student student6 = new Student(6, "林动", 18);
        Student student7 = new Student(7, "司马懿", 22);
        Student student8 = new Student(8, "王昭君", 20);
        Student student9 = new Student(1, "张三", 21);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        studentList.add(student7);
        studentList.add(student8);
        studentList.add(student9);
        return studentList;
    }
}

package com.prc.springbootlambda.common;

import com.prc.springbootlambda.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static List<Student> intiDataList1() {
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(1, "张三", "zhangsan", 21, "男", "浙江杭州");
        Student student2 = new Student(2, "李四", null, 15, "女", "浙江金华");
        Student student3 = new Student(3, "王二狗", "wangergou", 38, "男", null);
        Student student4 = new Student(4, "赵六", "zhaoliu", 51, "男", "江西南昌");
        Student student5 = new Student(5, "镜", null, 23, "女", "江西上饶");
        Student student6 = new Student(6, "林动", "lindong", 18, "男", null);
        Student student7 = new Student(7, "司马懿", null, 22, "男", "浙江杭州");
        Student student8 = new Student(8, "王昭君", "wangzhaojun", 20, "女", null);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        studentList.add(student7);
        studentList.add(student8);
        return studentList;
    }

    public static List<Student> intiDataList2() {
        List<Student> studentList = new ArrayList<>();
        Student student1 = new Student(1, "张三", "zhangsan", 21, "男", "浙江杭州");
        Student student2 = new Student(2, "李四", null, 15, "女", "浙江金华");
        Student student3 = new Student(3, "王二狗", "wangergou", 38, "男", null);
        Student student4 = new Student(4, "赵六", "zhaoliu", 51, "男", "江西南昌");
        Student student5 = new Student(5, "镜", null, 23, "女", "江西上饶");
        Student student6 = new Student(6, "林动", "lindong", 18, "男", null);
        Student student7 = new Student(7, "司马懿", null, 22, "男", "浙江杭州");
        Student student8 = new Student(8, "王昭君", "wangzhaojun", 20, "女", null);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
        studentList.add(student5);
        studentList.add(student6);
        studentList.add(student7);
        studentList.add(student8);
        return studentList;
    }

    public static Student initData() {
        return new Student(1,"张三", "张三", 20, "男", null);
    }
}

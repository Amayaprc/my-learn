package com.prc.springbootrabbitmqtest.test;

import java.lang.reflect.Method;

public class test5 {

    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("com.prc.springbootrabbitmqtest.pojo.User");
        Method[] methods = c1.getMethods();
        System.out.println(methods.toString());
    }
}

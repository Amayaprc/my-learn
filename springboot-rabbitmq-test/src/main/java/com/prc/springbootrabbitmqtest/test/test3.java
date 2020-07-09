package com.prc.springbootrabbitmqtest.test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class test3 {

    public static void main(String[] args) {
        Map map = new TreeMap();
        map.put("1","1");
        map.put("2","2");
        System.out.println(map.toString());
    }
}

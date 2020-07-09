package com.prc.springbootrabbitmqtest.test;

import java.util.*;

public class test1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        System.out.println("第一种：通过key索引获取值");
        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
        System.out.println("第二种：通过foreach循环");
        for (String str:list){
            System.out.println(str);
        }
        System.out.println("第三种：通过itertator迭代器");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
    }
}

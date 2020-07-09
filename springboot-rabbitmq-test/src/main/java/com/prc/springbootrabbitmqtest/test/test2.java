package com.prc.springbootrabbitmqtest.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class test2 {

    public static void main(String[] args) {
        Set set = new HashSet();
        set.add("a1");
        set.add("a2");
        set.add("a2");

        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            System.out.println(str);
        }
    }
}

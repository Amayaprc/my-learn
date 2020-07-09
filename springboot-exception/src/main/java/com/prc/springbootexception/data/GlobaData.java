package com.prc.springbootexception.data;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobaData {

    @ModelAttribute(value = "user")
    public Map<String,Object> myData(){
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("userName","liuguang");
        data.put("phoneNumber","15207055231");
        data.put("sex","男");
        return data;
    }
}

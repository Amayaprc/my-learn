package com.prc.springbootexception.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyException {

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView myException(NullPointerException e){
        ModelAndView model = new ModelAndView("error");
        model.addObject("message","空指针");
        return model;
    }
}

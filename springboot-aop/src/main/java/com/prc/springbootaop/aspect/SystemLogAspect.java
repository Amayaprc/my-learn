package com.prc.springbootaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SystemLogAspect {


    //Controller层切点
    @Pointcut("@annotation(com.prc.springbootaop.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    //Service层切点
    @Pointcut("@annotation(com.prc.springbootaop.annotation.SystemServiceLog)")
    public void serviceAspect() {
    }

    @Before("controllerAspect()")
    public void before(JoinPoint jp) {
        System.out.println("==============前置通知开始==============");
        System.out.println("请求接口:" + (jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()));
        System.out.println("请求人:" + "before");
        System.out.println("==============前置通知结束==============");
    }

    @After("serviceAspect()")
    public void after(JoinPoint jp) {
        System.out.println("==============后置通知开始==============");
        System.out.println("请求接口:" + (jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()));
        System.out.println("请求人:" + "After");
        System.out.println("==============后置通知结束==============");
    }

    @AfterReturning(value = "serviceAspect()",returning = "result")
    public void afterReturn(JoinPoint jp,Object result) {
        System.out.println("==============返回通知开始==============");
        System.out.println("请求接口:" + (jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()));
        System.out.println("请求人:" + "afterReturn");
        System.out.println("返回参数:" + result);
        System.out.println("==============返回通知结束==============");
    }

    @AfterThrowing(value = "controllerAspect()",throwing = "e")
    public void afterThrowing(JoinPoint jp,Exception e) {
        System.out.println("==============异常通知开始==============");
        System.out.println("异常接口:" + (jp.getTarget().getClass().getName() + "." + jp.getSignature().getName()));
        System.out.println("请求人:" + "afterReturn");
        System.out.println("异常代码:" + e.getClass().getName());
        System.out.println("异常信息:" + e.getMessage());
        System.out.println("==============异常通知结束==============");
    }

    @Around(value = "controllerAspect()")
    public Object afterThrowing(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("==============环绕通知开始==============");
        Object proceed = pjp.proceed();
        System.out.println("请求接口:" + (pjp.getTarget().getClass().getName() + "." + pjp.getSignature().getName()));
        System.out.println("请求人:" + "afterReturn");
        System.out.println("返回参数:" + proceed.toString());
        System.out.println("==============环绕通知结束==============");
        return proceed;
    }
}
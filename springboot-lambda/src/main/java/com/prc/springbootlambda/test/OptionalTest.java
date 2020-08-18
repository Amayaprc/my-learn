package com.prc.springbootlambda.test;

import com.prc.springbootlambda.common.Common;
import com.prc.springbootlambda.pojo.Student;

import java.util.Optional;

/**
 * Optional测试类
 */
public class OptionalTest {

    /**
     * 初始化数据
     */
    private static final Student student = Common.initData();

    /**
     * Optional.empty: 获取空的Optional实例,抛出NoSuchElementException异常
     */
    public static void optionalEmpty() {
        Optional<Student> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    /**
     * Optional.get: 如果在这个Optional中包含这个值则返回值,否则抛出NoSuchElementException异常
     */
    public static void optionalGet(String str) {
        Optional<String> optStr = Optional.of(str);
        System.out.println(optStr.get());
    }

    /**
     * Optional.of: 返回一个指定非null值的Optional,如果传入null依旧会抛出NullPointerException异常
     * 所以明确对象不为 null的时候使用Optional.of
     */
    public static void optionalOf(String str) {
        Optional<String> optStr = Optional.of(str);
    }

    /**
     * Optional.ofNullable: 如果为非空，返回Optional描述的指定值，否则返回空的 Optional
     * 如果对象即可能是 null也可能是非 null，就应该使用ofNullable()方法
     */
    public static void optionalOfNullAble(String str) {
        Optional<String> optStr = Optional.ofNullable(str);
    }

    /**
     * orElse(): 如果有值则返回该值,否则返回传递给它的参数值
     */
    public static void optionalOrElse(String str) {
        String test = Optional.ofNullable(str).orElse("Hello");
        System.out.println(test);
    }

    /**
     * orElseGet(): 如果有值则返回值,否则执行函数式接口，并将返回其执行结果
     */
    public static void optionalOrElseGet(String str) {
        String test = Optional.ofNullable(str).orElseGet(() -> "test");
        System.out.println(test);
    }

    /**
     * 当Optonal的值为null时,无论orElse还是orElseGet都会执行
     * 当Optional的值不为null时,orElse会执行,而orElseGet不会执行
     */
    public static void optionalTestElse(String str) {
        String test1 = Optional.ofNullable(str).orElse(test("a"));
        String test2 = Optional.ofNullable(str).orElseGet(() -> test("b"));
        System.out.println("a:" + test1);
        System.out.println("b:" + test2);
    }
    public static String test(String name) {
        System.out.println(name + "调用了该方法");
        return name;
    }

    /**
     * orElseThrow(): 如果有值则返回值,否则抛出异常,且可以由自己决定抛出什么异常
     */
    public static void optionalOrElseThrow(String str) {
        String test = Optional.ofNullable(str).orElseThrow(IndexOutOfBoundsException::new);
        System.out.println(test);
    }

    /**
     * isPresent(): 返回boolean,如果值不为null则返回true,否则返回false
     */
    public static void optionalIsPresent(String str) {
        boolean test = Optional.ofNullable(str).isPresent();
        System.out.println(test);
    }

    /**
     * ifPresent(): 如果值不为null则使用该值调用函数式接口,否则不做任何事情
     */
    public static void optionalIfPresent(String str) {
        Optional.ofNullable(str).ifPresent(System.out::println);
    }

    /**
     * map(): 如果值不为null,提供的映射方法
     */
    public static void optionalMap(Student student) {
        String name = Optional.ofNullable(student)
                .map(Student::getName).orElse("test");
        String address = Optional.ofNullable(student)
                .map(Student::getAddress).orElse("test");
        System.out.println(name + "," + address);
    }

    /**
     * flatMap(): 如果值不为null,返回基于Optional包含的映射方法的值,否则返回一个空的Optional
     */
    public static void optionalFlatMap(Student student) {
        String address = Optional.ofNullable(student).flatMap(s -> Optional.ofNullable(s.getName())).orElse("test");
        System.out.println(address);
    }

    /**
     * filter(): 如果值不为null,并且匹配给定的predicate,则返回一个Optional用以描述这个值，否则返回一个空的Optional
     */
    public static void optionalFilter(Student student) {
        Optional.ofNullable(student).filter(s -> s.getAge() > 18)
                .ifPresent(u ->  System.out.println("The student age is more than 18."));
    }

    public static void main(String[] args) {
        String str = null;

        //Optional.empty
        //OptionalTest.optionalEmpty();

        //Optional.get
        //OptionalTest.optionalGet(str);

        //Optional.of
        //OptionalTest.optionalOf(str);

        //Optional.ofNullable
        //OptionalTest.optionalOfNullAble(str);

        //orElse()
        //OptionalTest.optionalOrElse(str);

        //orElseGet()
        //OptionalTest.optionalOrElseGet(str);

        //orElse()和orElseGet()的区别
        //OptionalTest.optionalTestElse(str);

        //orElseThrow()
        //OptionalTest.optionalOrElseThrow(str);

        //isPresent()
        //OptionalTest.optionalIsPresent(str);

        //ifPresent()
        //OptionalTest.optionalIfPresent(str);

        //map()
        //OptionalTest.optionalMap(OptionalTest.student);

        //flatMap()
        //OptionalTest.optionalFlatMap(OptionalTest.student);

        //filter()
        //OptionalTest.optionalFilter(OptionalTest.student);
    }
}

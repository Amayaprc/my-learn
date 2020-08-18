package com.prc.springbootlambda.test;

import com.prc.springbootlambda.common.Common;
import com.prc.springbootlambda.pojo.Student;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Stream测试类
 */
public class StreamTest {

    /**
     * 初始化students1
     */
    public static List<Student> students1 = Common.intiDataList1();

    /**
     * 初始化students2
     */
    public static List<Student> students2 = Common.intiDataList2();

    /**
     * 初始化数据
     */
    private static Student student = Common.initData();

    public static void main(String[] args) {

        //遍历集合
        students1.forEach(student -> {
            System.out.println(student.getName());
        });

        //收集
        //收集名称
        List<String> list1 = students1.stream()
                .map(Student::getName)
                .collect(Collectors.toList());
        //收集不为null的地址
        List<String> list2 = students1.stream()
                .map(Student::getAddress)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        //收集不为null的地址
        String addresses = students1.stream()
                .map(Student::getAddress)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", "));

        //过滤
        //过滤出拼音不为null的数据并收集
        List<Student> list3 = students1.stream()
                .filter(student -> student.getPinyin() != null)
                .collect(Collectors.toList());
        //过滤出年龄在18以上的数据并收集
        List<Student> list4 = students1.stream()
                .filter(student -> student.getAge() >= 18)
                .collect(Collectors.toList());

        //去重
        List<Student> list5 = students1.stream()
                .distinct().collect(Collectors.toList());

        //获取最大值、最小值
        int max = students1.stream()
                .mapToInt(Student::getAge)
                .max()
                .orElse(0);
        int min = students1.stream()
                .mapToInt(Student::getAge)
                .min()
                .orElse(0);

        //获取最大值、最小值、累加和、平均数、总和
        IntSummaryStatistics ids = students1.stream().mapToInt(Student::getId).summaryStatistics();
        System.out.println("最大 : " + ids.getMax());
        System.out.println("最小 : " + ids.getMin());
        System.out.println("求和 : " + ids.getSum());
        System.out.println("平均 : " + ids.getAverage());
        System.out.println("统计 : " + ids.getCount());

        //累加
        int total = students1.stream()
                .map(Student::getId)
                .reduce((t, n) -> t + n).get();

        //统计
        long count = students1.stream()
                .filter(student -> student.getAddress() != null)
                .count();

        //分组
        Map<String, List<Student>> studentMaps = students1.stream()
                .collect(Collectors.groupingBy(Student::getSex));
        for (Map.Entry<String, List<Student>> studentMap : studentMaps.entrySet()) {
            System.out.println(studentMap.getKey() + studentMap.getValue().toString());
        }

        //分页,skip跳过指定数量的元素,limit获取指定数量的流
        int page = 1;
        int size = 2;
        List<Student> list6 = students1.stream()
                .skip((page-1) * size)
                .limit(size)
                .collect(Collectors.toList());

        //排序
        //根据年龄正序
        List<Student> list7 = students1.stream()
                .sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.toList());
        //根据年龄倒序
        List<Student> list8 = students1.stream()
                .sorted(Comparator.comparing(Student::getAge).reversed())
                .collect(Collectors.toList());
    }
}

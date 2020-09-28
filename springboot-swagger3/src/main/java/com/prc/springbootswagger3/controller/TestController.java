package com.prc.springbootswagger3.controller;

import com.prc.springbootswagger3.common.Common;
import com.prc.springbootswagger3.pojo.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Api(tags = "测试接口")
@RestController
@RequestMapping("test")
public class TestController {

    private static List<Student> students = Common.intiDataList();

    @ApiOperation("新增测试数据")
    @PostMapping
    public List<Student> add(Student student) {
        int max = students.stream().mapToInt(Student::getId).max().orElse(0);
        student.setId(max + 1);
        students.add(student);
        return students;
    }

    @ApiOperation("修改测试数据")
    @PutMapping
    public List<Student> update(Student student) {
        students.forEach(s -> {
            if (Objects.equals(s.getId(), student.getId())) {
                s.setName(student.getName());
                s.setAge(student.getAge());
            }
        });
        return students;
    }

    @ApiOperation("删除测试数据")
    @ApiImplicitParam(name = "id",
            value = "主键id",
            required = true,
            paramType = "query",
            dataType = "Integer")
    @DeleteMapping("{id}")
    public List<Student> delete(@PathVariable("id") Integer id) {
        students.removeIf(student -> Objects.equals(student.getId(), id));
        return students;
    }


    @ApiOperation("查询测试数据列表")
    @GetMapping("list")
    public List<Student> list() {
        return students;
    }

    @ApiOperation("根据id查询测试数据")
    @ApiImplicitParam(name = "id",
            value = "主键id",
            required = true,
            paramType = "query",
            dataType = "Integer")
    @GetMapping
    public Student getById(@RequestParam(value = "id") Integer id) {
        return students.stream()
                .filter(s -> Objects.equals(s.getId(), id)).findAny().orElse(null);
    }

    @ApiOperation("根据姓名和年龄查询测试数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "age", value = "年龄", required = true, paramType = "query", dataType = "Integer")
    })
    @GetMapping("getByNameAndAge")
    public Student getByNameAndAge(@RequestParam(value = "name") String name,
                                   @RequestParam(value = "age") Integer age) {
        return students.stream()
                .filter(s -> Objects.equals(s.getName(), name) && Objects.equals(s.getAge(), age))
                .findAny().orElse(null);
    }
}

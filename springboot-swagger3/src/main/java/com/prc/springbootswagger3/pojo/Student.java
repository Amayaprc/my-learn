package com.prc.springbootswagger3.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@ApiModel("学生")
@Data
@AllArgsConstructor
public class Student {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;
}

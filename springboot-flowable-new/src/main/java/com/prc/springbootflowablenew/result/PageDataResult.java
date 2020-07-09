package com.prc.springbootflowablenew.result;

import lombok.Data;

import java.util.List;

@Data
public class PageDataResult {

    //总记录数量
    private Integer totals;

    private List<?> list;
}

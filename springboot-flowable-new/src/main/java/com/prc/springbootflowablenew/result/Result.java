package com.prc.springbootflowablenew.result;

import com.prc.springbootflowablenew.enums.CodeEnums;
import lombok.Data;

@Data
public class Result {

    private Integer code;

    private String msg;

    private Object data;

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(CodeEnums codeEnums,String msg){
        return new Result(codeEnums.getCode(),msg);
    }

    public static Result success(CodeEnums codeEnums,String msg, Object data){
        return new Result(codeEnums.getCode(),msg,data);
    }

    public static Result fail(CodeEnums codeEnums,String msg){
        return new Result(codeEnums.getCode(),msg);
    }

    public static Result fail(CodeEnums codeEnums,String msg, Object data){
        return new Result(codeEnums.getCode(),msg,data);
    }
}

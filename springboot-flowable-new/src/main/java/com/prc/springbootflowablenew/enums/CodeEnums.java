package com.prc.springbootflowablenew.enums;

import lombok.Getter;

@Getter
public enum CodeEnums {

    SUCCESS(1),
    FAIL(2),
    EXCEPTION(3),
    ;

    private Integer code;

    CodeEnums(Integer code) {
        this.code = code;
    }
}

package com.prc.springbootwebsocket.pojo;

import lombok.Data;

@Data
public class Chat {

    private String from;

    private String content;

    private String to;
}

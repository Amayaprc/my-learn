package com.prc.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitHeadersConfig {

    public final static String HEADERSNAME = "AMAYA_HEADERS";

    public final static String QUEUENAME_SIX = "AMAYA_QUEUE_SIX";

    public final static String QUEUENAME_SEVEN = "AMAYA_QUEUE_SEVEN";

    //配置消息队列
    @Bean
    Queue queueSix(){
        return new Queue(QUEUENAME_SIX);
    }

    @Bean
    Queue queueSeven(){
        return new Queue(QUEUENAME_SEVEN);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
    @Bean
    HeadersExchange headersExchange(){
        //durable : 重启后是否依然有效
        //autoDelete : 长期未使用是否删除
        return new HeadersExchange(HEADERSNAME,true,false);
    }

    //配置绑定
    @Bean
    Binding bindingSix(){
        //whereAny : 匹配任意一个即匹配
        //whereAll : 全部匹配即匹配
        Map<String,Object> map = new HashMap<>();
        map.put("name","AMAYA");
        return BindingBuilder.bind(queueSix()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    Binding bindingSeven(){
        //只要有test字段即匹配
        return BindingBuilder.bind(queueSeven()).to(headersExchange()).where("test").exists();
    }
}

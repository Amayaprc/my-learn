package com.prc.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//广播模式
@Configuration
public class RabbitFanoutConfig {

    public final static String FANOUTNAME = "AMAYA_FANOUT";

    public final static String QUEUENAME_ONE = "AMAYA_QUEUE_ONE";

    public final static String QUEUENAME_TWO = "AMAYA_QUEUE_TWO";

    //配置消息队列
    @Bean
    Queue queueOne(){
        return new Queue(QUEUENAME_ONE);
    }

    @Bean
    Queue queueTwo(){
        return new Queue(QUEUENAME_TWO);
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
    FanoutExchange fanoutExchange(){
        //durable : 重启后是否依然有效
        //autoDelete : 长期未使用是否删除
        return new FanoutExchange(FANOUTNAME,true,false);
    }

    //配置绑定
    @Bean
    Binding bindingOne(){
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo(){
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }
}

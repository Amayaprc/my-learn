package com.prc.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//直连交换模式
@Configuration
public class RabbitDirectConfig {

    public final static String DIRECTNAME = "AMAYA_DIRECT";

    public final static String QUEUENAME = "AMAYA_QUEUE";

    public final static String ROUTINGKEY = "AMAYA_ROUTING";

    //配置消息队列
    @Bean
    Queue queue(){
        return new Queue(QUEUENAME);
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
    DirectExchange directExchange(){
        //durable : 重启后是否依然有效
        //autoDelete : 长期未使用是否删除
        return new DirectExchange(DIRECTNAME,true,false);
    }

    //配置绑定
    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(ROUTINGKEY);
    }
}

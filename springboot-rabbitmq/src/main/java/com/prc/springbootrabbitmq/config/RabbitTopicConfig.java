package com.prc.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTopicConfig {

    public final static String TOPICNAME = "AMAYA_TOPIC";

    public final static String QUEUENAME_THREE = "AMAYA_QUEUE_THREE";

    public final static String QUEUENAME_FOUR = "AMAYA_QUEUE_FOUR";

    public final static String QUEUENAME_FIVE = "AMAYA_QUEUE_FIVE";

    public final static String ROUTINGKEY_THREE = "AMAYA_ROUTING_THREE.#";

    public final static String ROUTINGKEY_FOUR = "AMAYA_ROUTING_FOUR.#";

    public final static String ROUTINGKEY_FIVE = "#.AMAYA_ROUTING_FIVE.#";

    //配置消息队列
    @Bean
    Queue queueThree(){
        return new Queue(QUEUENAME_THREE);
    }

    @Bean
    Queue queueFour(){
        return new Queue(QUEUENAME_FOUR);
    }

    @Bean
    Queue queueFive(){
        return new Queue(QUEUENAME_FIVE);
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
    TopicExchange topicExchange(){
        //durable : 重启后是否依然有效
        //autoDelete : 长期未使用是否删除
        return new TopicExchange(TOPICNAME,true,false);
    }

    //配置绑定
    @Bean
    Binding bindingThree(){
        return BindingBuilder.bind(queueThree()).to(topicExchange()).with(ROUTINGKEY_THREE);
    }

    @Bean
    Binding bindingFour(){
        return BindingBuilder.bind(queueFour()).to(topicExchange()).with(ROUTINGKEY_FOUR);
    }

    @Bean
    Binding bindingFive(){
        return BindingBuilder.bind(queueFive()).to(topicExchange()).with(ROUTINGKEY_FIVE);
    }
}

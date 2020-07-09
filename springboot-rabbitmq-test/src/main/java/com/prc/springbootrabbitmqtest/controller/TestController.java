package com.prc.springbootrabbitmqtest.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/direct")
    public void direct(){
        rabbitTemplate.convertAndSend("AMAYA_QUEUE","我是一条测试direct的信息鸭!");
    }

    @RequestMapping("/fanout")
    public void fanout(){
        rabbitTemplate.convertAndSend("AMAYA_FANOUT",null,"我是一条测试fanout的信息鸭!");
    }

    @RequestMapping("/topic")
    public void topic(Integer type){
        switch(type){
            case 1 :
                rabbitTemplate.convertAndSend("AMAYA_TOPIC","AMAYA_ROUTING_THREE.test","测试topic的THREE!");
                break;
            case 2 :
                rabbitTemplate.convertAndSend("AMAYA_TOPIC","AMAYA_ROUTING_FOUR.test","测试topic的FOUR!");
                break;
            case 3 :
                rabbitTemplate.convertAndSend("AMAYA_TOPIC","test.AMAYA_ROUTING_FIVE","测试topic的FIVE!");
                break;
            case 4 :
                rabbitTemplate.convertAndSend("AMAYA_TOPIC","AMAYA_ROUTING_THREE.AMAYA_ROUTING_FIVE","测试topic的THREE和FIVE!");
                break;
            default:
                rabbitTemplate.convertAndSend("AMAYA_TOPIC","AMAYA_ROUTING_FOUR.AMAYA_ROUTING_FIVE","测试topic的FOUR和FIVE!");
                break;
        }
    }

    @RequestMapping("/headers")
    public void headers(Integer type){
        Message message1 = MessageBuilder.withBody("我是一条测试headers1的信息鸭!".getBytes()).setHeader("name","AMAYA").build();
        Message message2 = MessageBuilder.withBody("我是一条测试headers2的信息鸭!".getBytes()).setHeader("test","test").build();
        if (type == 1){
            rabbitTemplate.send("AMAYA_HEADERS",null,message1);
        }else {
            rabbitTemplate.send("AMAYA_HEADERS",null,message2);
        }
    }
}

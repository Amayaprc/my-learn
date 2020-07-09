package com.prc.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    @RabbitListener(queues = "AMAYA_QUEUE_ONE")
    public void fanout1(String msg){
        System.out.println("fanout1 : " + msg);
    }

    @RabbitListener(queues = "AMAYA_QUEUE_TWO")
    public void fanout2(String msg){
        System.out.println("fanout2 : " + msg);
    }

}

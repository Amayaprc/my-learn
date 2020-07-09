package com.prc.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    @RabbitListener(queues = "AMAYA_QUEUE")
    public void direct(String msg){
        System.out.println("direct : " + msg);
    }

}

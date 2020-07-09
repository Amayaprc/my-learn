package com.prc.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeadersReceiver {

    @RabbitListener(queues = "AMAYA_QUEUE_SIX")
    public void headers1(byte[] msg){
        System.out.println("headers1 : " + new String(msg,0,msg.length));
    }

    @RabbitListener(queues = "AMAYA_QUEUE_SEVEN")
    public void headers2(byte[] msg){
        System.out.println("headers2 : " + new String(msg,0,msg.length));
    }
}

package com.prc.springbootrabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitListener(queues = "AMAYA_QUEUE_THREE")
    public void topic1(String msg){
        System.out.println("topic1 : " + msg);
    }

    @RabbitListener(queues = "AMAYA_QUEUE_FOUR")
    public void topic2(String msg){
        System.out.println("topic2 : " + msg);
    }

    @RabbitListener(queues = "AMAYA_QUEUE_FIVE")
    public void topic3(String msg){
        System.out.println("topic3 : " + msg);
    }
}

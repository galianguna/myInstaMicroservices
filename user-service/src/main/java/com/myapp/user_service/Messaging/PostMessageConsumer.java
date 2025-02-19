package com.myapp.user_service.Messaging;

import com.myapp.user_service.Dto.MessageDto;
import com.myapp.user_service.Service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PostMessageConsumer {


    @RabbitListener(queues = "userPostingQueue")
    public void consumeMessage(MessageDto messageDto){
        System.out.println("Hi message : "+messageDto.getContent());
    }
}

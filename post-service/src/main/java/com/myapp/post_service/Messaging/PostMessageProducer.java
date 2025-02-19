package com.myapp.post_service.Messaging;

import com.myapp.post_service.Dto.MessageDto;
import com.myapp.post_service.Model.Post;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PostMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public PostMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Post post){
        rabbitTemplate.convertAndSend("userPostingQueue",
                new MessageDto(
                        post.getPostId(),post.getContent(),post.getUserId()
                ));
    }

    public void sendStringMessage(String content) {
        rabbitTemplate.convertAndSend("userPostingQueue",
                new MessageDto(
                        null,content,null
                ));
    }
}

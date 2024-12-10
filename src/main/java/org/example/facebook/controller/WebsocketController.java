package org.example.facebook.controller;

import org.example.facebook.model.ConversationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class WebsocketController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

//    @MessageMapping("/chat.sendMessage")
//    public void sendMessage(@Payload ConversationUser conversationUser) {
//        // Tạo một kênh riêng cho người gửi và người nhận (private chat)
////        String channel = "/topic/private/" + generateChannel(conversationUser.getFromUser().getUsername(), conversationUser.getToUser().getUsername());
////        simpMessagingTemplate.convertAndSend(channel, conversationUser);
//        String channel = "/user/" + conversationUser.getToUser().getId();
//        simpMessagingTemplate.convertAndSend(channel, conversationUser);
//    }

    // Hàm tạo ID kênh cho chat 1-1
//    private String generateChannel(String user1, String user2) {
//        List<String> users = Arrays.asList(user1, user2);
//        Collections.sort(users); // Sắp xếp để đảm bảo thứ tự
//        return String.join("-", users); // Ghép ID thành "user1-user2"
//    }
}

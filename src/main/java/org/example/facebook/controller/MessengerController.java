package org.example.facebook.controller;

import org.example.facebook.dto.ConversationUserCreateDTO;
import org.example.facebook.dto.ShowConversationDTO;
import org.example.facebook.model.*;
import org.example.facebook.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/messengers")
@CrossOrigin("http://localhost:3000")
public class MessengerController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IConversationUserService conversationUserService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("")
    public ResponseEntity<List<ShowConversationDTO>> getUserConversations(Authentication authentication) {
        List<ShowConversationDTO> showConversationDTOList = conversationUserService.listUserConversation2(authentication.getName());
        return new ResponseEntity<>(showConversationDTOList, HttpStatus.OK);
    }

    @GetMapping("/{toId}")
    public ResponseEntity<List<ShowConversationDTO>> getConversation(Authentication authentication, @PathVariable("toId") Integer toId) {
        User fromUser = userService.show(authentication.getName());
        User toUser = userService.findById(toId);
        List<ShowConversationDTO> showConversationDTOList = conversationUserService.showConversation2(fromUser, toUser);
        messagingTemplate.convertAndSend("topic/private", toUser.getUsername());
        return new ResponseEntity<>(showConversationDTOList, HttpStatus.OK);
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ConversationUserCreateDTO conversationUserCreateDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User fromUser = userService.show(authentication.getName());
        User fromUser = userService.findById(conversationUserCreateDTO.getFromId());
        User toUser = userService.findById(conversationUserCreateDTO.getToId());

        // Tiến hành lưu tin nhắn vào database và gửi message qua WebSocket
        ConversationUser conversationUser = new ConversationUser();
        conversationUser.setFromUser(fromUser);
        conversationUser.setToUser(toUser);
        conversationUser.setCreateAt(LocalDateTime.now());
        conversationUser.setContent(conversationUserCreateDTO.getContent());

        conversationUserService.save(conversationUser);
        String channel = "/topic/private/" + generateChannel(fromUser.getId(), toUser.getId());

//        messagingTemplate.convertAndSend(channel, conversationUser);
        messagingTemplate.convertAndSendToUser(toUser.getUsername(), channel, conversationUser);
    }

    @MessageMapping("/chat.private")
    public void sendPrivateMessage(@Payload ConversationUser conversationUser) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User fromUser = userService.show(authentication.getName());
//        User fromUser = userService.findById()
        String channel = "/topic/private/" + generateChannel(conversationUser.getFromUser().getId(), conversationUser.getToUser().getId());
        messagingTemplate.convertAndSend(channel, conversationUser);
    }

    private String generateChannel(Integer fromUserId, Integer toUserId) {
        List<Integer> users = Arrays.asList(fromUserId, toUserId);
        Collections.sort(users); // Sắp xếp để đảm bảo thứ tự
        return users.get(0) + "-" + users.get(1); // Ghép ID thành "user1-user2"
    }

    @DeleteMapping("/{toId}")
    public ResponseEntity<ConversationUser> delete(@PathVariable("toId") Integer toId) {
        User toUser = userService.findById(toId);
        if (toUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ConversationUser conversationUser = new ConversationUser();
        conversationUser.setToUser(toUser);
        conversationUserService.delete(conversationUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

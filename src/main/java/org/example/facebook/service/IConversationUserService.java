package org.example.facebook.service;

import org.example.facebook.dto.ConversationDTO;
import org.example.facebook.dto.ShowConversationDTO;
import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.Conversation;
import org.example.facebook.model.ConversationUser;
import org.example.facebook.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IConversationUserService {
    ConversationUser findById(Integer id);
    void delete(ConversationUser conversationUser);
    void save(ConversationUser conversationUser);

    List<ShowConversationDTO> showConversation(Integer conversationId);
    List<ShowConversationDTO> showConversation2(User fromUser, User toUser);
    List<ShowConversationDTO> listUserConversation(String email);
    List<ShowConversationDTO> listUserConversation2(String email);
}

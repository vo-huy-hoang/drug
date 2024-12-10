package org.example.facebook.service.impl;

import org.example.facebook.dto.ConversationDTO;
import org.example.facebook.dto.ShowConversationDTO;
import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.Conversation;
import org.example.facebook.model.ConversationUser;
import org.example.facebook.model.User;
import org.example.facebook.repository.IConversationUserRepository;
import org.example.facebook.service.IConversationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationUserService implements IConversationUserService {
    @Autowired
    private IConversationUserRepository conversationUserRepository;

    @Override
    public ConversationUser findById(Integer id) {
        return conversationUserRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(ConversationUser conversationUser) {
        conversationUserRepository.delete(conversationUser);
    }

    @Override
    public void save(ConversationUser conversationUser) {
        conversationUserRepository.save(conversationUser);
    }

    @Override
    public List<ShowConversationDTO> showConversation(Integer conversationId) {
        return conversationUserRepository.showConversation(conversationId);
    }
    @Override
    public List<ShowConversationDTO> showConversation2(User fromUser, User toUser) {
        return conversationUserRepository.showConversation2(fromUser, toUser);
    }

    @Override
    public List<ShowConversationDTO> listUserConversation(String email) {
        return conversationUserRepository.listUserConversation(email);
    }

    @Override
    public List<ShowConversationDTO> listUserConversation2(String email) {
        return conversationUserRepository.listUserConversation2(email);
    }

}

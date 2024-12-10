package org.example.facebook.service.impl;

import org.example.facebook.model.Conversation;
import org.example.facebook.model.User;
import org.example.facebook.repository.IConversationRepository;
import org.example.facebook.service.IConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationService implements IConversationService {
    @Autowired
    private IConversationRepository conversationRepository;

    @Override
    public Conversation findById(Integer id) {
        return conversationRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Conversation conversation) {
        conversationRepository.save(conversation);
    }

    public Optional<Conversation> findExistingConversation(User fromUser, User toUser) {
        return conversationRepository.findConversationBetweenUsers(fromUser.getId(), toUser.getId());
    }
}

package org.example.facebook.service;

import org.example.facebook.model.Conversation;
import org.example.facebook.model.User;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IConversationService {
    Conversation findById(Integer id);
    void save(Conversation conversation);
    Optional<Conversation> findExistingConversation(User fromUser, User toUser);
}

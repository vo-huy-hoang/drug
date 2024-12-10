package org.example.facebook.service;

import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.Conversation;
import org.example.facebook.model.Notification;
import org.example.facebook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User showUsername(String username);
    User show(String email);
    Optional<User> findByUsername(String username);
    User findById(Integer id);
    void save(User user);
    UserDTO ViewAndEdit(String email);
    List<UserDTO> listFollow(String username);
    User findToUserByConversation(Conversation conversation, User fromUser);

    List<UserDTO> findByToUser(String email);
}

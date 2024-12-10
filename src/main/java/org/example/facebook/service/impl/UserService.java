package org.example.facebook.service.impl;

import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.Conversation;
import org.example.facebook.model.User;
import org.example.facebook.repository.IUserRepository;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User showUsername(String username) {
        return userRepository.showUsername(username);
    }

    @Override
    public User show(String email) {
        return userRepository.show(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDTO ViewAndEdit(String email) {
        return userRepository.ViewAndEdit(email);
    }

    @Override
    public List<UserDTO> listFollow(String email) {
        return userRepository.listFollow(email);
    }

    @Override
    public User findToUserByConversation(Conversation conversation, User fromUser) {
        return userRepository.findToUserByConversation(conversation, fromUser);
    }

    @Override
    public List<UserDTO> findByToUser(String email) {
        return userRepository.findByToUser(email);
    }

}

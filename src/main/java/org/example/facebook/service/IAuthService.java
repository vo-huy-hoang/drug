package org.example.facebook.service;

import org.example.facebook.dto.LoginDTO;
import org.example.facebook.dto.RegisterDTO;
import org.example.facebook.model.User;


public interface IAuthService {
    String login(LoginDTO loginDto);

    String register(RegisterDTO registerDto);
    User findByEmail (String email);
    User findById (Integer id);
}

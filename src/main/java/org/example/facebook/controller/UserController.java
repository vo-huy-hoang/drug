package org.example.facebook.controller;

import org.example.facebook.dto.UserDTO;
import org.example.facebook.model.User;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/list-user")
    public ResponseEntity<List<UserDTO>> getConversationWithMe(Authentication authentication) {
        List<UserDTO> userDTOList = userService.findByToUser(authentication.getName());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        UserDTO userDTO = userService.ViewAndEdit(authentication.getName());
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<User> edit(@RequestBody UserDTO userDTO, Authentication authentication) {
        User user = userService.show(authentication.getName());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());

        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
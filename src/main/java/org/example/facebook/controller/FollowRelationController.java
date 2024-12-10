package org.example.facebook.controller;

import org.example.facebook.dto.FollowRelationAndNotificationDTO;
import org.example.facebook.dto.FollowRelationShowDTO;
import org.example.facebook.dto.UserDTO;
import org.springframework.security.core.Authentication;
import org.example.facebook.model.FollowRelation;
import org.example.facebook.model.User;
import org.example.facebook.service.IFollowRelationService;
import org.example.facebook.service.INotificationService;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/followRelations")
@CrossOrigin("*")
public class FollowRelationController {
    @Autowired
    private IFollowRelationService followRelationService;
    @Autowired
    private IUserService userService;
    @Autowired
    private INotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<List<UserDTO>> showFollower(Authentication authentication) {
        List<UserDTO> userDTOList = userService.listFollow(authentication.getName());
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    @PostMapping("/{follower_id}")
    public ResponseEntity<?> followUser(@PathVariable("follower_id") Integer followerId, @RequestBody FollowRelationAndNotificationDTO followRelationAndNotificationDTO, Authentication authentication) {
        User user = userService.show(authentication.getName());
        User follower = userService.findById(followerId);

        boolean exist = followRelationService.existsByUserAndFollower(user, follower);
        if (!exist) {
            FollowRelation followRelation = new FollowRelation();
            followRelation.setUser(user);
            followRelation.setFollower(follower);
            followRelationService.save(followRelation);
            user.setQuantityFriend((user.getQuantityFriend() != null ? user.getQuantityFriend() : 0) + 1);
            userService.save(user);
            notificationService.createNotification(followerId, followRelationAndNotificationDTO.getContent() + follower.getNickname());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("")
    public ResponseEntity<FollowRelation> cancelFollow(@RequestParam("user_id") Integer userId, @RequestParam("follower_id") Integer followerId) {
        User user = userService.findById(userId);
        User follower = userService.findById(followerId);
        FollowRelation followRelation = followRelationService.findByUserAAndFollower(user, follower);
        if (followRelation != null) {
            followRelationService.delete(followRelation);
            user.setQuantityFriend(user.getQuantityFriend() - 1);
            userService.save(user);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package org.example.facebook.controller;

import org.example.facebook.dto.ReactShowDTO;
import org.example.facebook.model.Post;
import org.example.facebook.model.React;
import org.example.facebook.model.User;
import org.example.facebook.service.IPostService;
import org.example.facebook.service.IReactService;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reacts")
@CrossOrigin("*")
public class ReactController {
    @Autowired
    private IReactService reactService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;

    @GetMapping("/{post_id}")
    public ResponseEntity<List<ReactShowDTO>> getReacts(@PathVariable("post_id") Integer postId) {
        List<ReactShowDTO> reactShowDTOList = reactService.showReacts(postId);
        return new ResponseEntity<>(reactShowDTOList, HttpStatus.OK);
    }

    @PostMapping("/{post_id}")
    public ResponseEntity<?> create(@PathVariable("post_id") Integer postId, Authentication authentication) {
        User user = userService.show(authentication.getName());
        Post post = postService.findById(postId);
        boolean exist = reactService.existsByUserAndPost(user, post);
        if (!exist) {
            React react = new React();
            react.setUser(user);
            react.setPost(post);
            react.setIsTym(react.getIsTym());
            reactService.save(react);

            post.setQuantityReact((post.getQuantityReact() != null ? post.getQuantityReact() : 0) + 1);
            postService.save(post);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{post_id}")
    public ResponseEntity<React> delete(@PathVariable("post_id") Integer postId, Authentication authentication) {
        User user = userService.show(authentication.getName());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        React react = new React();
        react.setIsTym(!react.getIsTym());
        reactService.deleteReactsByPost(postId);

        Post post = react.getPost();
        if (post != null) {
            post.setQuantityReact((post.getQuantityReact() != null ? post.getQuantityReact() : 0) - 1);
            postService.save(post);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

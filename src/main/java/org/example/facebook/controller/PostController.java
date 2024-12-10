package org.example.facebook.controller;

import org.springframework.security.core.Authentication;
import org.example.facebook.dto.PostCreateDTO;
import org.example.facebook.dto.PostEditDTO;
import org.example.facebook.dto.PostShowDTO;
import org.example.facebook.model.Post;
import org.example.facebook.model.User;
import org.example.facebook.service.IPostService;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {
    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;

    @GetMapping("")
    public ResponseEntity<List<PostShowDTO>> getPosts() {
        List<PostShowDTO> postShowDTOList = postService.showPosts();
        return new ResponseEntity<>(postShowDTOList, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable("id") Integer id) {
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 401
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @PostMapping("")
    public ResponseEntity<?> create (@RequestBody PostCreateDTO postCreateDTO, Authentication authentication) {
        User user = userService.show(authentication.getName());
        Post post = new Post();
        post.setUser(user);
        post.setCreateAt(LocalDateTime.now());
        post.setTitle(postCreateDTO.getContent());
        post.setImg(postCreateDTO.getImg());
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> edit(@PathVariable("id") Integer id, @RequestBody PostEditDTO postEditDTO, Authentication authentication) {
        String username = authentication.getName();
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (post.getUser().getUsername().equals(username)) {
            post.setTitle(postEditDTO.getTitle());
            post.setImg(postEditDTO.getImg());
            postService.save(post);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable("id") Integer id, Authentication authentication) throws Exception {
        String email = authentication.getName();
        Post post = postService.findById(id);
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (post.getUser().getEmail().equals(email)) {
            postService.delete(post);
        } else {
            throw new Exception("User is not authorized to delete this post");
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
}

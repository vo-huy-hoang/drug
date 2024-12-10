package org.example.facebook.controller;

import org.example.facebook.dto.CommentCreateDTO;
import org.example.facebook.dto.CommentShowDTO;
import org.example.facebook.model.Comment;
import org.example.facebook.model.Post;
import org.example.facebook.model.User;
import org.example.facebook.service.ICommentService;
import org.example.facebook.service.IPostService;
import org.example.facebook.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin("*")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IPostService postService;

    @GetMapping("/{post_id}")
    public ResponseEntity<List<CommentShowDTO>> getComments(@PathVariable("post_id") Integer postId) {
        List<CommentShowDTO> commentShowDTOList = commentService.showComments(postId);
        return new ResponseEntity<>(commentShowDTOList, HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> create(@PathVariable("id") Integer id, @RequestBody CommentCreateDTO commentCreateDTO, Authentication authentication) {
        User user = userService.show(authentication.getName());
        Post post = postService.findById(id);
        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreateAt(LocalDateTime.now());
        comment.setContent(commentCreateDTO.getContent());
        commentService.save(comment);

        post.setQuantityComment((post.getQuantityComment() != null ? post.getQuantityComment() : 0) + 1);
        postService.save(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id, Authentication authentication) throws Exception {
        String username = authentication.getName();
        Comment comment = commentService.findById(id);
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (comment.getUser().getUsername().equals(username) || comment.getPost().getUser().getUsername().equals(username)) {
            commentService.delete(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new Exception("User is not authorized to delete this comment");
        }
    }
}

package org.example.facebook.service;

import org.example.facebook.dto.CommentShowDTO;
import org.example.facebook.model.Comment;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ICommentService {
    void save(Comment comment);
    List<CommentShowDTO> showComments(Integer postId);
    Comment findById(Integer id);
    void delete(Comment comment);
}

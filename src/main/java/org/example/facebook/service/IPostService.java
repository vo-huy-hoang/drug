package org.example.facebook.service;

import org.example.facebook.dto.PostShowDTO;
import org.example.facebook.model.Post;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IPostService {
    void save(Post post);
    void delete(Post post);
//    List<Map<String, Object>> showPosts();
//List<Post> showPosts();
    List<PostShowDTO> showPosts();
    Post findById(Integer id);
    void createPost (String title, String userId);
}

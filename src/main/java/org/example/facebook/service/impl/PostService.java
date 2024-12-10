package org.example.facebook.service.impl;

import org.example.facebook.dto.PostShowDTO;
import org.example.facebook.model.Post;
import org.example.facebook.repository.IPostRepository;
import org.example.facebook.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostService implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<PostShowDTO> showPosts() {
        return postRepository.showPosts();
    }

//    @Override
//    public List<Post> showPosts() {
//        return postRepository.showPosts();
//    }

//    @Override
//    public List<Map<String, Object>> showPosts() {
//        List<Object[]> results = postRepository.showPosts();
//        return results.stream().map(result -> {
//            Map<String, Object> postMap = new HashMap<>();
//            postMap.put("userId", result[0]);
//            postMap.put("username", result[1]);
//            postMap.put("title", result[2]);
//            postMap.put("body", result[3]);
//            return postMap;
//        }).collect(Collectors.toList());
//    }

    @Override
    public Post findById(Integer id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public void createPost(String title, String userId) {
        postRepository.createPost(title, userId);
    }
}

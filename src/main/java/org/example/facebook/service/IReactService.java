package org.example.facebook.service;

import org.example.facebook.dto.ReactShowDTO;
import org.example.facebook.model.Post;
import org.example.facebook.model.React;
import org.example.facebook.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReactService {
    void save (React react);
    void delete(React react);
    List<ReactShowDTO> showReacts(Integer postId);
    React findById(Integer id);
    void deleteById(Integer id);
    Boolean existsByUser(User user);
    Boolean existsByUserAndPost(User user, Post post);
    List<React> findByPost_Id(Integer postId);
    void deleteReactsByPost(Integer postId);
}

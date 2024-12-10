package org.example.facebook.service.impl;

import org.example.facebook.dto.ReactShowDTO;
import org.example.facebook.model.Post;
import org.example.facebook.model.React;
import org.example.facebook.model.User;
import org.example.facebook.repository.IReactRepository;
import org.example.facebook.service.IReactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactService implements IReactService {
    @Autowired private IReactRepository reactRepository;
    @Override
    public void save(React react) {
        reactRepository.save(react);
    }
    @Override
    public void delete(React react) {
        reactRepository.delete(react);
    }

    @Override
    public List<ReactShowDTO> showReacts(Integer postId) {
        return reactRepository.showReacts(postId);
    }


//    @Override
//    public List<React> findByPost_Id(Integer id) {
//        return reactRepository.findByPost_Id(id);
//    }

    @Override
    public React findById(Integer id) {
        return reactRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Integer id) {
        reactRepository.deleteById(id);
    }

    @Override
    public Boolean existsByUser(User user) {
        return reactRepository.existsByUser(user);
    }

    @Override
    public Boolean existsByUserAndPost(User user, Post post) {
        return reactRepository.existsByUserAndPost(user, post);
    }

    @Override
    public List<React> findByPost_Id(Integer postId) {
        return reactRepository.findByPost_Id(postId);
    }

    @Override
    public void deleteReactsByPost(Integer postId) {
        reactRepository.deleteReactsByPost(postId);
    }


}

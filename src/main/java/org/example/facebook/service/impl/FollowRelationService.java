package org.example.facebook.service.impl;

import org.example.facebook.dto.FollowRelationShowDTO;
import org.example.facebook.model.FollowRelation;
import org.example.facebook.model.User;
import org.example.facebook.repository.IFollowRelationRepository;
import org.example.facebook.service.IFollowRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowRelationService implements IFollowRelationService {
    @Autowired
    private IFollowRelationRepository followRelationRepository;
    @Override
    public boolean existsByUserAndFollower(User user, User follower) {
        return followRelationRepository.existsByUserAndFollower(user, follower);
    }

    @Override
    public void save(FollowRelation followRelation) {
        followRelationRepository.save(followRelation);
    }

    @Override
    public void delete(FollowRelation followRelation) {

    }

    @Override
    public FollowRelation findById(Integer id) {
        return null;
    }

    @Override
    public FollowRelation findByUserAAndFollower(User user, User follower) {
        return followRelationRepository.findByUserAndFollower(user, follower);
    }

    @Override
    public List<FollowRelationShowDTO> showFollowerShowDTO(String email) {
        return followRelationRepository.showFollowerShowDTO(email);
    }
}

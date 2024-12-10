package org.example.facebook.service;

import org.example.facebook.dto.FollowRelationShowDTO;
import org.example.facebook.model.FollowRelation;
import org.example.facebook.model.User;

import java.util.List;

public interface IFollowRelationService {
    boolean existsByUserAndFollower(User user, User follower);
    void save (FollowRelation followRelation);
    void delete(FollowRelation followRelation);
    FollowRelation findById(Integer id);
    FollowRelation findByUserAAndFollower(User user, User follower);
    List<FollowRelationShowDTO> showFollowerShowDTO(String email);
}

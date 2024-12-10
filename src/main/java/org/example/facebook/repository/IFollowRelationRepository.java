package org.example.facebook.repository;

import org.example.facebook.dto.FollowRelationShowDTO;
import org.example.facebook.model.FollowRelation;
import org.example.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFollowRelationRepository extends JpaRepository<FollowRelation, Integer> {
    boolean existsByUserAndFollower(User user, User follower);
    FollowRelation findByUserAndFollower(User user, User follower);
    @Query(value = "SELECT new org.example.facebook.dto.FollowRelationShowDTO(fr.id, u.id, u.avtUrl, u.username) from FollowRelation as fr join User as u on u.id = fr.follower.id and u.id = fr.user.id where u.email <> :email")
    List<FollowRelationShowDTO> showFollowerShowDTO(@Param("email") String email);
}

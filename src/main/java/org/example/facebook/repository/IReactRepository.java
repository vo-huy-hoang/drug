package org.example.facebook.repository;

import org.example.facebook.dto.ReactShowDTO;
import org.example.facebook.model.Post;
import org.example.facebook.model.React;
import org.example.facebook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IReactRepository extends JpaRepository<React, Integer> {
    @Query(value = "select new org.example.facebook.dto.ReactShowDTO(u.id, p.id, u.username) from React as r join User as u on r.user.id = u.id join Post as p on r.post.id = p.id where (:postId is null or p.id = :postId)")
    List<ReactShowDTO> showReacts(@Param("postId") Integer postId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO react (name, post_id, user_id) VALUES (:name, :postId, :userId)", nativeQuery = true)
    void insertReact(@Param("name") String name, @Param("postId") Long postId, @Param("userId") Long userId);
//    List<React> findByPost_Id(Integer id);
//    void deleteById(Integer id);
//    Boolean existsByUser(User user);
    Boolean existsByUserAndPost(User user, Post post);
    List<React> findByPost_Id(Integer postId);
    @Modifying
    @Transactional
    @Query("DELETE FROM React r WHERE r.post.id = :postId")
    void deleteReactsByPost(@Param("postId") Integer postId);

    Boolean existsByUser(User user);
}

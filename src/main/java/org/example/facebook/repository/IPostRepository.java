package org.example.facebook.repository;

import org.example.facebook.dto.PostShowDTO;
import org.example.facebook.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPostRepository extends JpaRepository<Post, Integer> {
//    @Query(value = "SELECT u.id, u.username, p.title, p.body FROM Post p JOIN User u ON p.user_id = u.id", nativeQuery = true)
//    List<Object[]> showPosts();
@Query(value = "SELECT new org.example.facebook.dto.PostShowDTO(p.id, u.id, u.email, u.username, p.title, p.img, p.createAt, u.avtUrl, p.quantityReact, p.quantityComment) FROM Post p join User u ON p.user.id = u.id")
List<PostShowDTO> showPosts();
@Query(value = "insert into post (title, user_id)\n" +
        "values (:title, :user_id) ", nativeQuery = true)
    void createPost (@Param("title") String title, @Param("user_id") String userId);
}


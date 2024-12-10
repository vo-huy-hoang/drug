package org.example.facebook.repository;

import org.example.facebook.dto.CommentShowDTO;
import org.example.facebook.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
//    List<Comment> findCommentByContent

//    List<Comment> findByProduct_IdAndCustomer_Id();
    @Query(value = "select new org.example.facebook.dto.CommentShowDTO(c.id, c.content, c.createAt, p.id, u.id, u.avtUrl, u.username, c.post.quantityReact, c.post.quantityComment) from Comment as c join User as u on c.user.id = u.id join Post as p on c.post.id = p.id where p.id = :postId")
    List<CommentShowDTO> showComments(@RequestParam("postId") Integer postId);
}

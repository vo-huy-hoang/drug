package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentShowDTO {
    private Integer id;
    private String content;
    private LocalDateTime creatAt;
    private Integer postId;
    private Integer userId;
    private String avtUrl;
    private String username;
    private Integer quantityReact;
    private Integer quantityComment;

}

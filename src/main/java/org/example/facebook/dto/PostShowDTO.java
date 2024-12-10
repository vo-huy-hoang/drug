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
public class PostShowDTO {
    private Integer id;
    private Integer userId;
    private String email;
    private String username;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private Boolean isEdit;
    private String img;
    private String avtUrl;
    private Integer quantityReact=0;
    private Integer quantityComment=0;

    public PostShowDTO(Integer id, Integer userId, String email,  String username, String title, String img, LocalDateTime createAt, String avtUrl, Integer quantityReact, Integer quantityComment) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.title = title;
        this.img = img;
        this.createAt = createAt;
        this.avtUrl = avtUrl;
        this.quantityReact = quantityReact;
        this.quantityComment = quantityComment;
    }
}

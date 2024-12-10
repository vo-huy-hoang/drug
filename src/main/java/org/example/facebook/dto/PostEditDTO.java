package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostEditDTO {
    private Integer id;
    private String img;
    private String username;
    private String title;
    private String body;
    private String createAt;
    private String isEdit;
}

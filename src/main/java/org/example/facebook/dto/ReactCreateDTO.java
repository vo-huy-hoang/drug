package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReactCreateDTO {
    private String userId;
    private String username;
    private String postId;
    private String name;
    private LocalDateTime createAt;
}

package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FollowRelationShowDTO {
    private Integer id;
    private Integer followerId;
    private String avtUrl;
    private String username;
}

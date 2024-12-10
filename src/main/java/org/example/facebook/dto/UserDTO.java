package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String avtUrl;
    private String username;
    private String email;
    private String nickname;
    private String phoneNumber;
    private String story;
    private Boolean gender;
    private Integer quantityFriend;


    public UserDTO(Integer id, String avtUrl, String username) {
        this.id = id;
        this.avtUrl = avtUrl;
        this.username = username;
    }
}

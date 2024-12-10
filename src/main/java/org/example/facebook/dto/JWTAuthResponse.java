package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.facebook.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTAuthResponse {
    private Integer userId;
    private String username;
    private String email;
    private String accessToken;
    private String tokenType = "Bearer";
    private Collection<? extends GrantedAuthority> roleList;
    public JWTAuthResponse(String accessToken, Collection<? extends GrantedAuthority> roleList) {
        this.accessToken = accessToken;
        this.roleList = roleList;
    }

    public JWTAuthResponse(String email) {
        this.email = email;
    }
}

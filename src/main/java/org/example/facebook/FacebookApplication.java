package org.example.facebook;

import com.fasterxml.classmate.members.RawMember;
import org.example.facebook.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;

@SpringBootApplication
public class FacebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacebookApplication.class, args);


    }
}

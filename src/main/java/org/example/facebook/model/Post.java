package org.example.facebook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name="img")
    private String img;
    @Column(name = "body")
    private String body;
    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "who_can_see")
    private String whoCanSee;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    private Boolean isEdit;
    private Integer quantityReact;
    private Integer quantityComment;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<React> reacts;

}

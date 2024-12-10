package org.example.facebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConversationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "from_id")
    private User fromUser;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "to_id")
    private User toUser;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
    @JoinColumn(name = "content")
    private String content;
    @JoinColumn(name = "create_at")
    private LocalDateTime createAt;
}

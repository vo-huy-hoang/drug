package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConversationUserCreateDTO {
    private Integer fromId;
    private String fromUsername;
    private Integer toId;
    private String content;
}

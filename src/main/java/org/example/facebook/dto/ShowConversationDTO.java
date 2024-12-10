package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowConversationDTO {
    private Integer conversationUserId;
    private String content;
    private LocalDateTime createAt;
    private Integer fromId;
    private String fromAvtUrl;
    private String fromUserName;
    private Integer toId;
    private String toAvtUrl;
    private String toUserName;
    public ShowConversationDTO(String toAvtUrl, Integer toId, String toUserName) {
        this.toAvtUrl = toAvtUrl;
        this.toId = toId;
        this.toUserName = toUserName;
    }
}

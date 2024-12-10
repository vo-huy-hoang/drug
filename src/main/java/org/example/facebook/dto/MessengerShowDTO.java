package org.example.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessengerShowDTO {
    private String content;
    private Integer chatBoxId;
    private Integer fromId;
    private Integer toId;
}

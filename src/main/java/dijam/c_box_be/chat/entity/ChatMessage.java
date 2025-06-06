package dijam.c_box_be.chat.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String roomId;
    private String sender;
    private String content;
}

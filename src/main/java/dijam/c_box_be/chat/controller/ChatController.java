package dijam.c_box_be.chat.controller;

import dijam.c_box_be.chat.entity.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/message") // /pub/chat/message
    @SendTo("/sub/chat/room/1") // 구독한 사용자에게 전달
    public ChatMessage send(ChatMessage message) {
        return message; // 그대로 전송 (DB 저장하려면 여기서 저장도 가능)
    }
}

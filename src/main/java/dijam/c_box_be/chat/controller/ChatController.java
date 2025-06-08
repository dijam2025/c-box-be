package dijam.c_box_be.chat.controller;

import dijam.c_box_be.chat.entity.ChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat/message") // 클라이언트 → 서버
    @SendTo("/sub/chat/room/1") // 서버 → 구독중인 클라이언트
    public ChatMessage send(ChatMessage message) {
        return message;
    }
}

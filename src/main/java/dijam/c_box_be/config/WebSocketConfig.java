package dijam.c_box_be.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 받을 prefix
        config.enableSimpleBroker("/sub");
        // 메시지 보낼 prefix
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 WebSocket 연결 시 사용할 엔드포인트
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*") // CORS 허용
                .withSockJS();
    }
}


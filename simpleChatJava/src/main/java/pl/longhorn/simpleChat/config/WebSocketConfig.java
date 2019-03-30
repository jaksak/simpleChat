package pl.longhorn.simpleChat.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import pl.longhorn.simpleChat.controller.SocketController;
import pl.longhorn.simpleChat.service.Broadcaster;
import pl.longhorn.simpleChat.service.MessageService;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Broadcaster broadcaster;
    @Autowired
    private MessageService messageService;


    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketController(broadcaster, objectMapper, messageService), "/chat/socket");
    }

}
package pl.longhorn.simpleChat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import pl.longhorn.simpleChat.model.MessageInputData;
import pl.longhorn.simpleChat.service.Broadcaster;
import pl.longhorn.simpleChat.service.MessageService;

@AllArgsConstructor
public class SocketController extends TextWebSocketHandler {

    private Broadcaster broadcaster;
    private ObjectMapper objectMapper;
    private MessageService messageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        broadcaster.add(session);
        messageService.getAllMessage().forEach(message -> broadcaster.sendMessageToSpecificUser(session, message));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MessageInputData inputData = objectMapper.readValue(message.getPayload(), MessageInputData.class);
        messageService.addMessage(inputData);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        broadcaster.remove(session);
    }
}

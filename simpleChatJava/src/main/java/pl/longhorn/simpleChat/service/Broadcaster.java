package pl.longhorn.simpleChat.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import pl.longhorn.simpleChat.model.Message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor
public class Broadcaster {

    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper;

    public void add(WebSocketSession session) {
        sessions.add(session);
    }

    public void remove(WebSocketSession session) {
        sessions.remove(session);
    }

    public void sendMessage(Message message) {
        sessions.forEach(session -> sendMessaceToSpecificUser(session, messageToJson(message)));
    }

    public void sendMessageToSpecificUser(WebSocketSession session, Message message) {
        sendMessaceToSpecificUser(session, messageToJson(message));
    }

    @SneakyThrows
    private String messageToJson(Message message) {
        return objectMapper.writeValueAsString(message.toView());
    }

    @SneakyThrows
    private void sendMessaceToSpecificUser(WebSocketSession session, String jsonMessage) {
        session.sendMessage(new TextMessage(jsonMessage));
    }
}

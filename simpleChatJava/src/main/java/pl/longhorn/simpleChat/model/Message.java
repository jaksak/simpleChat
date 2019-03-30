package pl.longhorn.simpleChat.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class Message {
    private String id = UUID.randomUUID().toString();
    private LocalDateTime sendingTime;
    private LocalDateTime serverTime = LocalDateTime.now();
    private String content;
    private String author;

    public MessageView toView(){
        return MessageView.builder()
                .id(id)
                .sendingTime(sendingTime.toString())
                .serverTime(serverTime.toString())
                .content(content)
                .author(author)
                .build();
    }
}

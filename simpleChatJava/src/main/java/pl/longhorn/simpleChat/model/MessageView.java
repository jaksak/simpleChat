package pl.longhorn.simpleChat.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MessageView {
    private String id;
    private String sendingTime;
    private String serverTime;
    private String content;
    private String author;
}

package pl.longhorn.simpleChat.service;

import lombok.RequiredArgsConstructor;
import pl.longhorn.simpleChat.model.Message;
import pl.longhorn.simpleChat.model.MessageInputData;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor
public class MessageService {

    private final CopyOnWriteArrayList<Message> messages = new CopyOnWriteArrayList<Message>();
    private final Broadcaster broadcaster;

    public List<Message> getAllMessage() {
        return messages;
    }

    public Message addMessage(MessageInputData inputData) {
        Message message = inputData.toMessage();
        messages.add(message);
        broadcaster.sendMessage(message);
        return message;
    }

    public List<Message> getMessagesAfter(String lastMessageId) {
        List<Message> toReturn = new CopyOnWriteArrayList<>();
        boolean isAfter = false;
        for (Message message :
                messages) {
            if (isAfter) {
                toReturn.add(message);
            } else if (message.getId().equals(lastMessageId)) {
                isAfter = true;
            }
        }
        return toReturn;
    }

    public void clear(){
        messages.clear();
    }
}

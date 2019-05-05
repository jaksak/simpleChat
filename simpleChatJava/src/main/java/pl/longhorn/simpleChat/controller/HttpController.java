package pl.longhorn.simpleChat.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.longhorn.simpleChat.model.Message;
import pl.longhorn.simpleChat.model.MessageInputData;
import pl.longhorn.simpleChat.model.MessageView;
import pl.longhorn.simpleChat.service.MessageService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("chat/http")
public class HttpController {

    MessageService messageService;

    @CrossOrigin
    @GetMapping()
    public List<MessageView> getMessagesAfter(@RequestParam(required = false) String lastMessageId, HttpServletResponse response) {
        response.addHeader("Connection", "close");

        List<Message> messages = lastMessageId == null ? messageService.getAllMessage() : messageService.getMessagesAfter(lastMessageId);
        return messages.stream()
                .map(Message::toView)
                .collect(Collectors.toList());
    }

    @CrossOrigin
    @PostMapping
    public String sendMessage(@RequestBody MessageInputData inputData) {
        return messageService.addMessage(inputData).getId();
    }

    @GetMapping("/clear")
    public void clear() {
        messageService.clear();
    }
}

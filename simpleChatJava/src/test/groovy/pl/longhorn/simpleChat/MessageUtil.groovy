package pl.longhorn.simpleChat

import pl.longhorn.simpleChat.model.MessageInputData

import java.time.LocalDateTime

class MessageUtil {

    static final ANY_AUTHOR = "ANY_AUTHOR"
    static final ANY_CONTENT = 'ANY_CONTENT'
    static final ANY_DATE = LocalDateTime.now()

    static MessageInputData createInputData() {
        MessageInputData.builder()
                .author(ANY_AUTHOR)
                .content(ANY_CONTENT)
                .sendingTime(ANY_DATE)
                .build()
    }
}

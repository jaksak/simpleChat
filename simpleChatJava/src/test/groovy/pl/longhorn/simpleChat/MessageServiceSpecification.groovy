package pl.longhorn.simpleChat


import pl.longhorn.simpleChat.service.Broadcaster
import pl.longhorn.simpleChat.service.MessageService
import spock.lang.Specification

class MessageServiceSpecification extends Specification {

    def broadcaster = Mock(Broadcaster.class)
    MessageService messageService;

    def setup() {
        messageService = new MessageService(broadcaster)
    }

    def "after adding message, new message should be returned"() {
        given:
        def inputData = MessageUtil.createInputData();
        when:
        def sizeBeforeAdding = messageService.getAllMessage().size()
        messageService.addMessage(inputData)
        def sizeAfterAdding = messageService.getAllMessage().size();
        then:
        sizeBeforeAdding + 1 == sizeAfterAdding
        def lastMessage = messageService.getAllMessage().last();
        lastMessage.author == MessageUtil.ANY_AUTHOR
        lastMessage.content == MessageUtil.ANY_CONTENT
        lastMessage.sendingTime == MessageUtil.ANY_DATE
    }

    def "using get message after, old message should be not returned"() {
        when:
        messageService.addMessage(MessageUtil.createInputData());
        String lastId = messageService.getAllMessage().last().id
        then:
        messageService.getMessagesAfter(lastId).size() == 0
    }
}
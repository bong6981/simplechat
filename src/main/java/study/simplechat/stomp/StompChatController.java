package study.simplechat.stomp;

import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Profile("stomp")
@RequiredArgsConstructor
@Controller
public class StompChatController {

    private static final String MEMBER_IN_MESSAGE = "님이 입장했습니다";
    private final SimpMessagingTemplate template;

    @MessageMapping("/chat/notice/join")
    public void handleMessage(ChatMessage message) {
        log.info(message);
        message.setMessage(message.getWriter() + MEMBER_IN_MESSAGE);
        template.convertAndSend("/topic/chat/room/" + message.getChatRoomId(), message);
    }

    @MessageMapping("/chat/message")
    public void handleGreeting(ChatMessage message) {
        log.info(message);
        template.convertAndSend("/topic/chat/room/" + message.getChatRoomId(), message);
    }
}

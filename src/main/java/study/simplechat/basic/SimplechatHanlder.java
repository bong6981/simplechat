package study.simplechat.basic;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class SimplechatHanlder extends TextWebSocketHandler {

    private final ChatRoom chatRoom;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String payLoad = message.getPayload();
        log.info("message : {}", message.getPayload());
        chatRoom.handleMessage(session, payLoad);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        chatRoom.join(session);
        log.info("client join, session = {}", session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("client leave, session = {}", session.getId());
        chatRoom.leave(session);
    }
}

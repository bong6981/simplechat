package study.simplechat.basic;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ChatRoom {
    private final String USER_IN_MESSAGE = "새로운 사용자가 입장했습니다";
    private final String USER_OUT_MESSAGE = "사용자가 방을 나갔습니다";
    private final Set<WebSocketSession> webSocketSessions = new HashSet<>();

    public void handleMessage(WebSocketSession session, String message) {
        sendMessage(message);
    }

    public void join(WebSocketSession session) {
        webSocketSessions.add(session);
        sendMessage(USER_IN_MESSAGE);
    }


    public void leave(WebSocketSession session) {
        webSocketSessions.remove(session);
        sendMessage(USER_OUT_MESSAGE);
    }

    private void sendMessage(String message) {
        webSocketSessions.parallelStream().forEach(session -> {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}

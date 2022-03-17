package study.simplechat.basic;

import java.io.IOException;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SimplechatHanlder extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException, InterruptedException {
        log.info("message : {}", message.getPayload());

        TextMessage welcomeMessage = new TextMessage("Welcome!!");
        session.sendMessage(welcomeMessage);
        Thread.sleep(3000);
        session.sendMessage(welcomeMessage);
    }

}

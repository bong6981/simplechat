package study.simplechat.stomp;

import lombok.Getter;

@Getter
public class ChatMessage {

    private Long chatRoomId;
    private String writer;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }
}

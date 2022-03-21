package study.simplechat.stomp;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class ChatRoom {
    private Long id;
    private String name;

    public ChatRoom(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

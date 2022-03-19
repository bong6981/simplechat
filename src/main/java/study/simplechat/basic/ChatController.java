package study.simplechat.basic;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Profile("!stomp")
@RequestMapping("/chat")
@Controller
public class ChatController {

    @GetMapping
    public String getChatRoom() {
        return "/basic/room";
    }
}

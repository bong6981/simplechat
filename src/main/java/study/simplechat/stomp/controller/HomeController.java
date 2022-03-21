package study.simplechat.stomp.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Profile("stomp")
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/users/nicknameForm";
    }
}

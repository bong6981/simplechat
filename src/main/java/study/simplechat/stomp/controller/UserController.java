package study.simplechat.stomp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import study.simplechat.stomp.HttpSessionUtils;

@Profile("stomp")
@RequestMapping("/users")
@Controller
public class UserController {

    @GetMapping("/nicknameForm")
    public String nicknameForm() {
        return "stomp/form";
    }

    @PostMapping("/nickname")
    public String nickname(String nickname, HttpSession session) {
        HttpSessionUtils.setNickname(session, nickname);
        return "redirect:/chat/rooms";
    }
}

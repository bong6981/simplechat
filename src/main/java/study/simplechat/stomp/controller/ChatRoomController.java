package study.simplechat.stomp.controller;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import study.simplechat.stomp.ChatRoom;
import study.simplechat.stomp.ChatRoomRepository;
import study.simplechat.stomp.HttpSessionUtils;

@Log4j2
@Profile("stomp")
@RequiredArgsConstructor
@RequestMapping("/chat/rooms")
@Controller
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping
    public String getChatRooms(Model model, HttpSession session) {
        if(!HttpSessionUtils.hasNickname(session)) {
            return "redirect:/users/nicknameForm";
        }
        model.addAttribute("rooms", chatRoomRepository.findAll());
        return "/stomp/rooms";
    }

    @GetMapping("/{roomId}")
    public String getChatRoom(@PathVariable Long roomId, Model model, HttpSession session) {
        if(!HttpSessionUtils.hasNickname(session)) {
            return "redirect:/users/nicknameForm";
        }
        ChatRoom room = chatRoomRepository.findById(roomId).orElseThrow(IllegalStateException::new);
        model.addAttribute("room", room);
        model.addAttribute("member", HttpSessionUtils.getNickname(session).get());
        return "/stomp/room";
    }
}

package study.simplechat.stomp.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import study.simplechat.stomp.ChatRoom;
import study.simplechat.stomp.ChatRoomRepository;


@Profile("stomp")
@RequiredArgsConstructor
@Configuration
public class DumpDataConfig implements ApplicationRunner {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        chatRoomRepository.save(new ChatRoom("채팅룸1"));
        chatRoomRepository.save(new ChatRoom("채팅룸2"));
        chatRoomRepository.save(new ChatRoom("채팅룸3"));
        chatRoomRepository.save(new ChatRoom("채팅룸4"));
        chatRoomRepository.save(new ChatRoom("채팅룸5"));
        chatRoomRepository.save(new ChatRoom("채팅룸6"));
        chatRoomRepository.save(new ChatRoom("채팅룸7"));
        chatRoomRepository.save(new ChatRoom("채팅룸8"));
    }
}

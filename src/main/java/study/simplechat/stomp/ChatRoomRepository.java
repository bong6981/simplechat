package study.simplechat.stomp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("stomp")
@Repository
public class ChatRoomRepository {

    private static final Map<Long, ChatRoom> chatRooms = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public ChatRoom save(ChatRoom chatRoom) {
        chatRoom.setId(++sequence);
        chatRooms.put(chatRoom.getId(), chatRoom);
        return chatRoom;
    }

    public Optional<ChatRoom> findById(Long id) {
        return Optional.ofNullable(chatRooms.get(id));
    }

    public List<ChatRoom> findAll() {
        return new ArrayList<>(chatRooms.values());
    }
}

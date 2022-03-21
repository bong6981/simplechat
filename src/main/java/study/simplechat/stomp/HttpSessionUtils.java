package study.simplechat.stomp;

import java.util.Optional;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {

    public static final String NICKNAME_KEY = "nickname";

    public static boolean hasNickname(HttpSession session) {
        return session.getAttribute(NICKNAME_KEY) != null;
    }

    public static void setNickname(HttpSession session, String nickname) {
        session.setAttribute(NICKNAME_KEY, nickname);
    }

    public static Optional<String> getNickname(HttpSession session) {
        return Optional.ofNullable((String) session.getAttribute(NICKNAME_KEY));
    }
}

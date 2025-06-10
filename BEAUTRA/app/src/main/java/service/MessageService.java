package service;

import model.Message;
import util.JsonUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class MessageService {
    private static final String FILE_PATH = "src/main/resources/data/messages.json";

    public void sendMessage(String from, String to, String content) {
        List<Message> messages = JsonUtil.readJson(FILE_PATH, Message.class);
        messages.add(new Message("MSG-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
                from, to, content, LocalDateTime.now().toString()));
        JsonUtil.writeJson(FILE_PATH, messages);
    }

    public List<Message> getMessages(String user1, String user2) {
        List<Message> messages = JsonUtil.readJson(FILE_PATH, Message.class);
        return messages.stream().filter(m ->
            (m.getFrom().equals(user1) && m.getTo().equals(user2)) ||
            (m.getFrom().equals(user2) && m.getTo().equals(user1))
        ).collect(Collectors.toList());
    }
}

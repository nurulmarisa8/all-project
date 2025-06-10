package controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Message;
import service.MessageService;

public class ChatController {
    @FXML private ListView<String> messageList;
    @FXML private TextField messageField;

    private final MessageService messageService = new MessageService();
    private String currentUserId;
    private String recipientId;

    public void initChat(String currentUserId, String recipientId) {
        this.currentUserId = currentUserId;
        this.recipientId = recipientId;
        for (Message msg : messageService.getMessages(currentUserId, recipientId)) {
            messageList.getItems().add(msg.getFrom() + ": " + msg.getMessage());
        }
    }

    public void sendMessage() {
        String text = messageField.getText();
        if (!text.isEmpty()) {
            messageService.sendMessage(currentUserId, recipientId, text);
            messageList.getItems().add(currentUserId + ": " + text);
            messageField.clear();
        }
    }
}

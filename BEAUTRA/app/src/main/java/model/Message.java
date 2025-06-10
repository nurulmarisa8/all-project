package model;

public class Message {
    private String id;
    private String from;
    private String to;
    private String message;
    private String timestamp;

    public Message(String id, String from, String to, String message, String timestamp) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getId() { return id; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getMessage() { return message; }
    public String getTimestamp() { return timestamp; }
}

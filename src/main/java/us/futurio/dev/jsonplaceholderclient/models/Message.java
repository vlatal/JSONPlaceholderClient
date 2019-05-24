package us.futurio.dev.jsonplaceholderclient.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@JsonPropertyOrder({"timestamp", "message"})
public class Message {
    private ZonedDateTime timestamp;
    private String message;

    public Message(ZonedDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public static Message createWithNow(String message) {
        return new Message(ZonedDateTime.now(), message);
    }
}

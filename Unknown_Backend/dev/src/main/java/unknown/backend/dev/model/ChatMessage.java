package unknown.backend.dev.model;
// WebSocketChattingModel

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    // MessageType
    // JOIN, CHAT, LEAVE
    private MessageType type;

    // RoomId
    private String roomId;

    // Sender
    private String sender;

    // Content
    private String message;

}
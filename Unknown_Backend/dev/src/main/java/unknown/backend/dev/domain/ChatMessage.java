package unknown.backend.dev.domain;
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
        ENTER, TALK, EXIT
    }

    // MessageType
    // JOIN, CHAT, EXIT
    private MessageType type;

    // RoomId
    private String roomId;

    // Sender
    private String sender;

    // Content
    private String message;
}
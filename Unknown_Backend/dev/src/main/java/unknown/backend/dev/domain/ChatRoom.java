package unknown.backend.dev.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom {
    // 방 상태 enum class (대기, 대화중, 종료)
    public enum RoomStatus {
        WAITING, TALKING, EXIT
    }
    // 방 번호
    private String roomId;

    // 유저 제한 기본 값 2
    public static final Integer limitUser = 2;

    // 연결되어 있는 유저의 수 기본 값 1
    private Integer connectionUser = 1;

    // 방 상태 기본 상태 WAITING
    private RoomStatus status = RoomStatus.WAITING;


    public static ChatRoom create() {
        ChatRoom room = new ChatRoom();
        room.roomId = UUID.randomUUID().toString();
        return room;
    }
}

package unknown.backend.dev.service;

import unknown.backend.dev.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private Map<String, ChatRoom> chatRooms;

    @PostConstruct
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom() {
        List<ChatRoom> chatRoomList = new ArrayList<>(chatRooms.values());
        Collections.reverse(chatRoomList);
        return chatRoomList;
    }

    public ChatRoom findRoomById(String id) {
        return chatRooms.get(id);
    }

    public ChatRoom createRoom() {
        ChatRoom chatRoom = ChatRoom.create();
        chatRooms.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public String enterRoom(){

        List<ChatRoom> chatRoomList = findAllRoom();

        if(chatRoomList.size()==0){
            log.info("방이 없습니다. 방을 생성 합니다.");
            ChatRoom createdRoom = createRoom();
            String roomId = createdRoom.getRoomId();
            log.info("생성된 방 번호 : " + roomId);
            return roomId;
        }

        log.info("방이 존재합니다. 방에 입장합니다.");
        for (ChatRoom chatRoom : chatRoomList) {
            // 현재 방의 유저 수가 제한 수 보다 작으면 True
            if (chatRoom.getConnectionUser() < ChatRoom.limitUser) {

                log.info("방 번호 : " + chatRoom.getRoomId() + " / 현재 유저 수 : " + chatRoom.getConnectionUser());

                // 현재 유저 수
                Integer nowUser = chatRoom.getConnectionUser();

                // 현재 유저 수 +1로 변경
                chatRoom.setConnectionUser(nowUser + 1);

                // 만약 현재 유저 수가 제한 수와 같다면 방 상태를 TALKING으로 변경
                if (chatRoom.getConnectionUser().equals(ChatRoom.limitUser)) {
                    chatRoom.setStatus(ChatRoom.RoomStatus.TALKING);
                }

                // 방 번호 반환
                return chatRoom.getRoomId();

            }
        }
        log.info("모든 방이 꽉 찼습니다.");
        return "FULL";
    }

    // TODO : 랜덤채팅 퇴장 시, 방 상태 변경
    public String exitRoom(String roomId){
        ChatRoom chatRoom = findRoomById(roomId);
        Integer nowUser = chatRoom.getConnectionUser();
        chatRoom.setConnectionUser(nowUser - 1);
        if(chatRoom.getConnectionUser().equals(0)){
            chatRoom.setStatus(ChatRoom.RoomStatus.EXIT);
        }
        else if (chatRoom.getConnectionUser().equals(1)){
            chatRoom.setStatus(ChatRoom.RoomStatus.WAITING);
        }
        return roomId;
    }

    public ChatRoom startRandomChatting(){

        // roomId 혹은 "모든 방이 꽉 찼습니다." 반환
        log.info("랜덤 채팅을 시작합니다.");

        String roomResult = enterRoom();
        log.info("방 번호 : " + roomResult);

        // 방이 꽉 차있는 경우, 새로운 방 생성
        if(roomResult.equals("FULL")){
            log.info("모든 방이 가득 차 있습니다. 방을 생성합니다.");
            ChatRoom createdRoom = createRoom();
            return findRoomById(createdRoom.getRoomId());
        }
        // 방 번호 반환
        else{
            log.info("방 번호 : " + roomResult);
            return findRoomById(roomResult);
        }
    }
}

package unknown.backend.dev.service;

import unknown.backend.dev.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.repository.UserRepository;

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

        if(chatRoomList.isEmpty()){
            ChatRoom createdRoom = createRoom();
            return createdRoom.getRoomId();
        }

        for (ChatRoom chatRoom : chatRoomList) {
            if (chatRoom.getConnectionUser() < ChatRoom.limitUser) {
                Integer nowUser = chatRoom.getConnectionUser();
                chatRoom.setConnectionUser(nowUser + 1);
                if (chatRoom.getConnectionUser().equals(ChatRoom.limitUser)) {
                    chatRoom.setStatus(ChatRoom.RoomStatus.TALKING);
                }
                return chatRoom.getRoomId();
            }
        }
        return "FULL";
    }


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
        String roomResult = enterRoom();
        if(roomResult.equals("FULL")){
            ChatRoom createdRoom = createRoom();
            return findRoomById(createdRoom.getRoomId());
        }
        else{
            return findRoomById(roomResult);
        }
    }

    public User getLoginUserByEmail(String email) {
        return UserService.findByEmail(email);
    }
}

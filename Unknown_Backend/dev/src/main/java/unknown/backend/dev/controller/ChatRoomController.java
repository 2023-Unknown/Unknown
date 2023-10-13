package unknown.backend.dev.controller;

import unknown.backend.dev.domain.ChatRoom;
import unknown.backend.dev.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatRoomController {
    private final ChatService chatService;

    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    // 모든 채팅방 리스트
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    @GetMapping("/room/enter")
    @ResponseBody
    public ChatRoom startChatting() {
        return chatService.startRandomChatting();
    }

    @GetMapping("/room/make")
    @ResponseBody
    public ChatRoom makeRoom() {
        return chatService.createRoom();
    }

    // 채팅방 정보
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }

}

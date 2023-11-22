package unknown.backend.dev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import unknown.backend.dev.domain.ChatRoom;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/chat")
@Api(tags={"ChatRoomAPI"})
public class ChatRoomController {
    private final ChatService chatService;

    @Autowired
    public ChatRoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }

    // 모든 채팅방 리스트
    @GetMapping("/rooms")
    @ResponseBody
    @ApiOperation(value="모든 채팅방 리스트",notes="모든 채팅방 리스트를 반환합니다.")
    public List<ChatRoom> room() {
        return chatService.findAllRoom();
    }

    @GetMapping("/room/enter")
    @ResponseBody
    @ApiOperation(value="랜덤 채팅방 입장",notes="랜덤 채팅방에 입장합니다.")
    public ChatRoom startChatting(Authentication auth) {
        User chattingUser = chatService.getLoginUserByEmail(auth.getName());
        if (chattingUser == null) {
            return null;
        }
        return chatService.startRandomChatting();
    }

    @GetMapping("/room/make")
    @ResponseBody
    @ApiOperation(value="채팅방 생성",notes="채팅방을 생성합니다.")
    public ChatRoom makeRoom() {
        return chatService.createRoom();
    }

    // 채팅방 정보
    @GetMapping("/room/{roomId}")
    @ResponseBody
    @ApiOperation(value="채팅방 정보",notes="채팅방 정보를 반환합니다.")
    @ApiImplicitParam(name = "roomId", value = "채팅방 아이디",paramType = "path")
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findRoomById(roomId);
    }

}

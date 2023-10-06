package unknown.backend.dev.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.dto.UserDTO;
import unknown.backend.dev.service.UserService;

@RestController
@RequiredArgsConstructor
@NoArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    @GetMapping("/detail/{username}")
    public ResponseEntity<UserDTO> getUserDetailByUsername(@PathVariable String username) {

        return
    }

    @GetMapping("/detail/{email}")
    public String getUserDetailByEmail(@PathVariable String email) {
        return userService.findByEmail(email).toString();
    }

    @GetMapping("/detail/{phoneNumber}")
    public String getUserDetailByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber).toString();
    }





}

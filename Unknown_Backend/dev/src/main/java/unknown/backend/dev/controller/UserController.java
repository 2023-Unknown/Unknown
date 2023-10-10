package unknown.backend.dev.controller;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.UserDTO;
import unknown.backend.dev.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/detail/{method}/{value}")
    public ResponseEntity<User> getUserDetail(@PathVariable String method, @PathVariable String value) {
        User user = userService.findByMethodAndValue(method, value);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/list")
    public String getUserList() {
        return userService.findAll().toString();
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(username, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        User user = userService.deleteUser(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}

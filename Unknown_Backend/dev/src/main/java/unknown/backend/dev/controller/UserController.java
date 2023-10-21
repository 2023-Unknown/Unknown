package unknown.backend.dev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportDTO;
import unknown.backend.dev.dto.UserDTO;
import unknown.backend.dev.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags={"UserAPI"})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/detail/{method}/{value}")
    @ApiOperation(value="특정 유저 조회",notes="유저 정보를 조회합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "method", value = "조회 방식"),
            @ApiImplicitParam(name = "value", value = "조회방식에 맞는 유저 정보 ex)이메일, 이름.." )
    })
    public ResponseEntity<User> getUserDetail(@PathVariable String method, @PathVariable String value) {
        User user = userService.findByMethodAndValue(method, value);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/list")
    @ApiOperation(value="유저 리스트 조회",notes="유저 리스트를 조회합니다.")
    public String getUserList() {
        return userService.findAll().toString();
    }

    @PostMapping("/create")
    @ApiOperation(value="유저 생성",notes="유저를 생성합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userDTO", value = "생성할 유저의 정보")
    })
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/update/{username}")
    @ApiOperation(value="유저 수정",notes="유저 정보를 수정합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "수정할 유저의 이름"),
            @ApiImplicitParam(name = "userDTO", value = "수정할 유저의 정보")
    })
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(username, userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{username}")
    @ApiOperation(value="유저 삭제",notes="유저를 삭제합니다.")
    @ApiImplicitParam(name = "username", value = "삭제할 유저의 이름")
    public ResponseEntity<User> deleteUser(@PathVariable String username) {
        User user = userService.deleteUser(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

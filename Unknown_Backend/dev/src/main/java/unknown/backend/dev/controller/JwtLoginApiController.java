package unknown.backend.dev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.Util.JwtTokenUtil;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.LoginRequest;
import unknown.backend.dev.dto.RegisterDTO;
import unknown.backend.dev.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt-login")
public class JwtLoginApiController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody RegisterDTO joinRequest) {

        // loginId 중복 체크
        if(userService.checkEmailDuplicate(joinRequest.getEmail())) {
            return "이메일이 중복됩니다.";
        }
        userService.registerUser(joinRequest);
        return "회원가입 성공";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        User user = userService.login(loginRequest);

        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
        if(user == null) {
            return"로그인 아이디 또는 비밀번호가 틀렸습니다.";
        }

        // 로그인 성공 => Jwt Token 발급
        String secretKey = "my-secret-key-123123";
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분

        return JwtTokenUtil.createToken(user.getEmail(), secretKey, expireTimeMs);
    }

    @GetMapping("/info")
    public String userInfo(Authentication auth) {
        User loginUser = userService.getLoginUserByEmail(auth.getName());

        return String.format("Email : %s\nUsername : %s\nrole : %s",
                loginUser.getEmail(), loginUser.getUsername(), loginUser.getRole().name());
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "관리자 페이지 접근 성공";
    }
}

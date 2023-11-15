package unknown.backend.dev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jwt-login")
public class JwtLoginApiController {

    private final UserService userService;
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

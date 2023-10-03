package unknown.backend.dev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtController {
    private final AuthService authService;

    @GetMapping("/signUp")
    public String signUp(){
        return "user/signUp";
    }
    @PostMapping("/signUp")
    public String signUp(@Validated UserSignUpRequest signUpReq) throws Exception{
        User user = authService.signUp(signUpReq)
    }

}

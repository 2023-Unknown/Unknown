package unknown.backend.dev.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("userDetauls = " + userDetails);
        if(userDetails != null){
            User user = userService.findByEmail(userDetails.getUsername());
            .orElseThrow(()-> new UserNotFoundException());

            model.addAttribute("userDetail",userDetail);

        }
        return "user/profile"
    }

    @GetMapping("/profile/{username}")
    public String profile(Model model, @PathVariable String username){
        User user = userService.findByUsername(username)
            .orElseThrow(()-> new UserNotFoundException());
        model.addAttribute("userDetail",user);
        return "user/profile";
    }

    @GetMapping("userList")
    public String showUserList(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList",userList);

        return "user/userList";
    }
}

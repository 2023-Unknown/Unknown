package unknown.backend.dev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import unknown.backend.dev.service.MailService;

@Controller
@RequiredArgsConstructor
@Api(tags = {"MailAPI"})
@RequestMapping("/api/v1")
public class MailController {

    private final MailService mailService;

    @GetMapping("/mail")
    public String MailPage(){
        return "/email/mail";
    }

    @ResponseBody
    @PostMapping("/mail")
    @ApiOperation(value = "메일 전송 API", notes = "메일을 전송합니다.")
    @ApiImplicitParam(name ="mail", value = "인증번호를 받을 이메일 주소", paramType = "query")
    public String MailSend(String mail){

        int number = mailService.sendMail(mail);

        return String.valueOf(number);
    }

}
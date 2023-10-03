package unknown.backend.dev.user.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
public class UserSignInRequest {
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;

    @NotEmpty(message="비밀번호는 필수 입력 값입니다.")
    private String password;

    @Builder
    public UserSignInRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}

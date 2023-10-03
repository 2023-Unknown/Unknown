package unknown.backend.dev.user.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import unknown.backend.dev.user.domain.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@ToString
public class UserSignUpRequest {
    @NotEmpty(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;
    @NotEmpty(message="비밀번호는 필수 입력 값입니다.")
    private String password;

    @NotEmpty(message="이름은 필수 입력 값입니다.")
    private String name;

    @Builder
    public UserSignUpRequest(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User toUserEntity(){
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .build();
    }

}

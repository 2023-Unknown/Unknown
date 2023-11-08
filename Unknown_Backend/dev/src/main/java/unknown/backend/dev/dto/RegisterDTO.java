package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import unknown.backend.dev.domain.User;

@Getter
@Setter
@Builder
public class RegisterDTO {
    @ApiModelProperty(example = "유저 이름")
    private final String username;
    @ApiModelProperty(example = "유저 비밀번호")
    private final String password;
    @ApiModelProperty(example = "유저 비밀번호 확인")
    private final String passwordConfirm;
    @ApiModelProperty(example = "유저 이메일")
    private final String email;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .build();
    }
}

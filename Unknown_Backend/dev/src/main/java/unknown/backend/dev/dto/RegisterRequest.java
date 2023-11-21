package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.domain.UserRole;

@Getter
@Setter
@Builder
public class RegisterRequest {
    @ApiModelProperty(example = "유저 이름")
    private final String username;
    @ApiModelProperty(example = "유저 비밀번호")
    private final String password;
    @ApiModelProperty(example = "유저 비밀번호 확인")
    private final String passwordConfirm;
    @ApiModelProperty(example = "유저 이메일")
    private final String email;
    @ApiModelProperty(example = "주로 사용하는 언어")
    private final String language;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .email(email)
                .language(language)
                .build();
    }
}

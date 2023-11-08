package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateDTO {
    @ApiModelProperty(example = "유저 이름")
    private final String username;
    @ApiModelProperty(example = "유저 비밀번호")
    private final String password;
    @ApiModelProperty(example = "유저 이메일")
    private final String email;
}

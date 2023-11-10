package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateDTO {
    @ApiModelProperty(example = "유저 이름")
    public final String name;
    @ApiModelProperty(example = "유저 비밀번호")
    public final String password;
    @ApiModelProperty(example = "유저 이메일")
    public final String email;
}

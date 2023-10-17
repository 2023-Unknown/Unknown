package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

// 요청 시, 아래의 규격에 맞게 요청 및 전달
@Getter
public class UserDTO {
    @ApiModelProperty(example = "유저 이름")
    private String username;
    @ApiModelProperty(example = "유저 비밀번호")
    private String password;
    @ApiModelProperty(example = "유저 이메일")
    private String email;
    @ApiModelProperty(example = "유저 전화번호")
    private String phoneNumber;
    @ApiModelProperty(example = "유저 프로필 사진 URL")
    private String profileImage;
    @ApiModelProperty(example = "유저 관심사")
    private String interest;
    @ApiModelProperty(example = "유저 소개")
    private String introduction;
    @ApiModelProperty(example = "유저 신고 횟수")
    private Integer reportCount;

    // 일반 생성자
    public UserDTO(String username,
                   String password,
                   String email,
                   String phoneNumber,
                   String profileImage,
                   String interest,
                   String introduction,
                   Integer reportCount)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.interest = interest;
        this.introduction = introduction;
        this.reportCount= reportCount;
    }
}

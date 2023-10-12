package unknown.backend.dev.dto;

import lombok.Getter;

// 요청 시, 아래의 규격에 맞게 요청 및 전달
@Getter
public class UserDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String profileImage;
    private String interest;
    private String introduction;
    private int reportCount;

    // 일반 생성자
    public UserDTO(String username,String password, String email,String phoneNumber,String profileImage,String interest,String introduction,int reportCount){
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

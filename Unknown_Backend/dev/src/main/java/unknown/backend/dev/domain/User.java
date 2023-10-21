package unknown.backend.dev.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import unknown.backend.dev.common.domain.CoreEntity;
import unknown.backend.dev.dto.UserDTO;

@Entity
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@NoArgsConstructor
@ToString
public class User extends CoreEntity {
    @NotBlank
    @Size(max = 255)
    @Column(name = "username", nullable = false, unique = true)
    @ApiModelProperty(example = "유저 이름")
    private String username;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password", nullable = false)
    @ApiModelProperty(example = "유저 비밀번호")
    private String password;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email", nullable = false, unique = true)
    @ApiModelProperty(example = "유저 이메일")
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "phone_number", nullable = false, unique = true)
    @ApiModelProperty(example = "유저 전화번호")
    private String phoneNumber;

    @Size(max = 255)
    @Column(name = "profile_image_url", nullable = true, columnDefinition = "varchar(255) DEFAULT 'null'")
    @ApiModelProperty(example = "유저 프로필 사진 URL")
    private String profileImage;

    @Size(max = 255)
    @Column(name = "interest", nullable = true, columnDefinition = "varchar(255) DEFAULT 'null'")
    @ApiModelProperty(example = "유저 관심사")
    private String interest;

    @Size(max = 255)
    @Column(name = "introduction", nullable = true, columnDefinition = "varchar(255) DEFAULT 'null'")
    @ApiModelProperty(example = "유저 소개")
    private String introduction;

    @Column(name = "is_active", nullable = true, columnDefinition = "boolean DEFAULT true")
    @ApiModelProperty(example = "유저 신고 횟수")
    private boolean isActive;
    // 신고 당한 횟수
    @Column(name = "report_count", nullable = true, columnDefinition = "int DEFAULT 0")
    @ApiModelProperty(example = "유저 신고 횟수")
    private int reportCount;

    @Builder
    public User(String username, String password, String email, String phoneNumber,
            String profileImage, String interest, String introduction) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.interest = interest;
        this.introduction = introduction;
        this.isActive = true;
        this.reportCount = 0;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public static UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO(
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getProfileImage(),
                user.getInterest(),
                user.getIntroduction(),
                user.getReportCount());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail())
                .introduction(userDTO.getIntroduction())
                .interest(userDTO.getInterest())
                .phoneNumber(userDTO.getPhoneNumber())
                .profileImage(userDTO.getProfileImage())
                .build();

        return user;
    }
}

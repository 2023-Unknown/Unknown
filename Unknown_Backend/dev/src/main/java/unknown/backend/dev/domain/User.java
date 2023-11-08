package unknown.backend.dev.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import unknown.backend.dev.common.domain.CoreEntity;
import unknown.backend.dev.dto.RegisterDTO;

@Entity
@Getter
@Setter
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

    @Column(name = "is_active", nullable = true, columnDefinition = "boolean DEFAULT true")
    @ApiModelProperty(example = "유저 신고 횟수")
    private boolean isActive;
    // 신고 당한 횟수
    @Column(name = "report_count", nullable = true, columnDefinition = "int DEFAULT 0")
    @ApiModelProperty(example = "유저 신고 횟수")
    private int reportCount;

    @Column(name = "role", nullable = true, columnDefinition = "varchar(255) DEFAULT 'ROLE_USER'")
    @Enumerated(value = EnumType.STRING)
    @ApiModelProperty(example = "유저 권한")
    private UserRole role;

    @Builder
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = true;
        this.reportCount = 0;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public static RegisterDTO toDTO(User user) {

        return RegisterDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public static User toModel(RegisterDTO registerDTO) {

        return User.builder()
                .username(registerDTO.getUsername())
                .password(registerDTO.getPassword())
                .email(registerDTO.getEmail())
                .build();
    }
}

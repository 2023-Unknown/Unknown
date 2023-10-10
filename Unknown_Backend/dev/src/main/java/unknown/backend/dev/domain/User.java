package unknown.backend.dev.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import unknown.backend.dev.common.domain.CoreEntity;
import unknown.backend.dev.dto.UserDTO;

import java.time.LocalDateTime;

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
    private String username;

    @NotBlank
    @Size(max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank
    @Email
    @Size(max = 255)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 255)
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Size(max = 255)
    @Column(name = "profile_image_url", nullable = true, columnDefinition = "varchar(255) DEFAULT 'null'")
    private String profileImage;

    @Size(max = 255)
    @Column(name = "interest", nullable = true, columnDefinition = "varchar(255) DEFAULT 'null'")
    private String interest;

    @Size(max = 255)
    @Column(name = "introduction", nullable = true, columnDefinition = "varchar(255) DEFAULT 'null'")
    private String introduction;

    @Column(name = "is_active", nullable = true, columnDefinition = "boolean DEFAULT true")
    private boolean isActive;

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
                user.getIntroduction());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
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

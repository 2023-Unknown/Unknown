package unknown.backend.dev.domain;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import unknown.backend.dev.common.domain.CoreEntity;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@NoArgsConstructor
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
        this.password = password; // Consider hashing the password before setting it.
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.interest = interest;
        this.introduction = introduction;
    }

    public boolean isActive() {
        return this.isActive;
    }

}

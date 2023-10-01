package unknown.backend.dev.model;

import javax.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

    // UserId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // UserName
    @Column(name = "username", columnDefinition = "varchar(255) NOT NULL", unique = true)
    private String username;

    // Password (Hashed)
    @Column(name = "password", columnDefinition = "varchar(255) NOT NULL", unique = true)
    private String password;

    // Email (Hashed)
    @Column(name = "email", columnDefinition = "varchar(255) NOT NULL", unique = true)
    private String email;

    // PhoneNumber (Hashed)
    @Column(name = "phone_number", columnDefinition = "varchar(255) NOT NULL", unique = true)
    private String phoneNumber;

    // ProfileImageUrl
    @Column(name = "profile_image_url", columnDefinition = "varchar(255) NULL DEFAULT 'null'")
    private String profileImage;

    // Interest
    @Column(name = "interest", columnDefinition = "varchar(255) NULL DEFAULT 'null'")
    private String interest;

    // Introduction
    @Column(name = "introduction", columnDefinition = "varchar(255) NULL DEFAULT 'null'")
    private String introduction;

    // CreateDate
    @CreatedDate
    @Column(name = "create_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createAt;

    // UpdateDate
    @LastModifiedDate
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP NULL DEFAULT NULL")
    private LocalDateTime updateAt;

    // DeleteDate (Soft Delete)
    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP NULL DEFAULT NULL")
    private LocalDateTime deletedAt;

    @PreRemove
    public void preRemove() {
        this.deletedAt = LocalDateTime.now();
    }

}

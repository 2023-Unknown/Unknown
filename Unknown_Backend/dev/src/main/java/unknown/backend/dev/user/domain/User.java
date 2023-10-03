package unknown.backend.dev.user.domain;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import unknown.backend.dev.common.domain.CoreEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class User extends CoreEntity {
    @Column(nullable=false,unique=true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length =0,nullable = false)
    private String name;

    @Builder
    public User(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

    public boolean checkPassword(PasswordEncoder passwordEncoder, String password){
        return passwordEncoder.matches(password,this.password);
    }

}

package unknown.backend.dev.common.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

// get, set, 생성일, 수정일, 삭제일을 자동으로 만들어주는 클래스
@Getter
@Setter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class CoreEntity {
    @CreatedDate
    private LocalDate createdAt;

    @LastModifiedDate
    private LocalDate updatedAt;

    private LocalDateTime deletedAt;
    @PreRemove
    public void preRemove() {
        this.deletedAt = LocalDateTime.now();
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
}
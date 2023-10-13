package unknown.backend.dev.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@ToString
public class Report {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter", nullable = false)
    private String reporter;

    @Column(name = "reported", nullable = false)
    private String reported;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "report_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate reportDate;

    @Builder
    public Report(String reporter, String reported, String reason, LocalDate reportDate) {
        this.reporter = reporter;
        this.reported = reported;
        this.reason = reason;
        this.reportDate = reportDate;
    }
}
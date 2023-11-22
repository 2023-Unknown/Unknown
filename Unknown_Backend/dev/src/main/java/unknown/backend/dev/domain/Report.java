package unknown.backend.dev.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "reports")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@ToString
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long Id;

    @Column(name = "reporter", nullable = false)
    @Email
    private String reporterEmail;

    @Column(name = "reported", nullable = false)
    @Email
    private String reportedEmail;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "report_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate reportDate;
    @Builder
    public Report(String reporterEmail, String reportedEmail, String reason) {
        this.reporterEmail = reporterEmail;
        this.reportedEmail = reportedEmail;
        this.reason = reason;
        this.reportDate = LocalDate.now();
    }
}
package unknown.backend.dev.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
public class ReportDTO {
    // 신고한 유저
    private String reporter;

    // 신고 당한 유저
    private String reported;

    // 신고 사유
    private String reason;

    // 신고 날짜
    private LocalDate reportDate;

    public ReportDTO(String reporter, String reported, String reason) {
        this.reporter = reporter;
        this.reported = reported;
        this.reason = reason;
        this.reportDate = LocalDate.now();
    }
}

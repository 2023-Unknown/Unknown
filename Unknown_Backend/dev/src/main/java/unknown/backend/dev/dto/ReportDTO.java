package unknown.backend.dev.dto;

import java.time.LocalDateTime;

public class ReportDTO {
    // 신고한 유저
    private String reporter;

    // 신고 당한 유저
    private String reported;

    // 신고 사유
    private String reason;

    // 신고 날짜
    private LocalDateTime reportDate;
}

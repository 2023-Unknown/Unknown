package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
public class ReportDTO {
    @ApiModelProperty(example = "신고한 유저")
    private String reporter;

    @ApiModelProperty(example = "신고 당한 유저")
    private String reported;

    @ApiModelProperty(example = "신고 사유")
    private String reason;

    @ApiModelProperty(example = "신고 날짜")
    private LocalDate reportDate;

    public ReportDTO(String reporter, String reported, String reason) {
        this.reporter = reporter;
        this.reported = reported;
        this.reason = reason;
        this.reportDate = LocalDate.now();
    }
}

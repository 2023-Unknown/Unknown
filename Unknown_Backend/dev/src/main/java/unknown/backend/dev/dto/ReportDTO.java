package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReportDTO {
    @ApiModelProperty(example = "신고한 유저")
    private final String reporterEmail;

    @ApiModelProperty(example = "신고 당한 유저")
    private final String reportedEmail;

    @ApiModelProperty(example = "신고 사유")
    private final String reason;

    @ApiModelProperty(example = "신고 날짜")
    private final LocalDate reportDate;
}

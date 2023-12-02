package unknown.backend.dev.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportRequest {
    @ApiModelProperty(example = "신고한 유저")
    private final String reporterEmail;

    @ApiModelProperty(example = "신고 당한 유저")
    private final String reportedEmail;

    @ApiModelProperty(example = "신고 사유")
    private final String reason;
}

package unknown.backend.dev.exception.constant;

import lombok.Getter;

@Getter
public enum ReportExceptionMessages {
    CANT_REPORT_YOURSELF("자기 자신을 신고할 수 없습니다."),
    CANT_REPORT_NOT_EXIST_USER("존재하지 않는 유저를 신고할 수 없습니다."),
    CANT_REPORT_THE_SAME_PERSON_WITHIN_A_DAY("동일인물을 1일이내에 신고할 수 없습니다.");
    private final String message;
    ReportExceptionMessages(final String message){
        this.message = message;
    }
}

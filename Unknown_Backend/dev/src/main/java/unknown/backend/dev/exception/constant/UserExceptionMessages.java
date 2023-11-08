package unknown.backend.dev.exception.constant;

import lombok.Getter;

@Getter
public enum UserExceptionMessages {
    USER_NOT_FOUND("해당 유저를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("이미 존재하는 유저입니다."),
    USER_NOT_AUTHORIZED("해당 유저는 권한이 없습니다."),
    USER_NOT_VALID("유효하지 않은 유저입니다."),
    USER_NOT_ALLOWED("해당 유저는 허용되지 않습니다."),
    NOT_ALLOWED_ACCESS("올바르지 않은 접근입니다.");
    private final String message;

    UserExceptionMessages(String message) {
        this.message = message;
    }
}

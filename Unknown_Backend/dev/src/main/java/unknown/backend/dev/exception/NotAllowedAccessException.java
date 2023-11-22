package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.UserExceptionMessages;

public class NotAllowedAccessException extends IllegalArgumentException{
    public NotAllowedAccessException() {
        super(UserExceptionMessages.NOT_ALLOWED_ACCESS.getMessage());
    }
}

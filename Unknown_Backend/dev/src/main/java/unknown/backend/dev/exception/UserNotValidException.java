package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.UserExceptionMessages;

public class UserNotValidException extends IllegalArgumentException{
    public UserNotValidException() {
        super(UserExceptionMessages.USER_NOT_VALID.getMessage());
    }
}

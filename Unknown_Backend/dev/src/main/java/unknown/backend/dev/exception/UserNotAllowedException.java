package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.UserExceptionMessages;

public class UserNotAllowedException extends IllegalArgumentException{
    public UserNotAllowedException() {
        super(UserExceptionMessages.USER_NOT_ALLOWED.getMessage());
    }
}

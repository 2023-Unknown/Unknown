package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.UserExceptionMessages;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(UserExceptionMessages.USER_NOT_FOUND.getMessage());
    }
}
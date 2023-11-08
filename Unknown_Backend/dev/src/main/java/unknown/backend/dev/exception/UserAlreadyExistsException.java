package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.UserExceptionMessages;

public class UserAlreadyExistsException extends IllegalArgumentException{
    public UserAlreadyExistsException() {
        super(UserExceptionMessages.USER_ALREADY_EXISTS.getMessage());
    }
}

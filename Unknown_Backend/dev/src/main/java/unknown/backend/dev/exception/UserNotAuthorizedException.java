package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.UserExceptionMessages;

public class UserNotAuthorizedException extends IllegalArgumentException{
    public UserNotAuthorizedException() {
        super(UserExceptionMessages.USER_NOT_AUTHORIZED.getMessage());
    }
}

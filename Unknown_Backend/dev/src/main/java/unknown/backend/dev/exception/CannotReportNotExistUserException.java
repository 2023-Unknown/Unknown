package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.ReportExceptionMessages;

public class CannotReportNotExistUserException extends IllegalArgumentException{
    public CannotReportNotExistUserException() {
        super(ReportExceptionMessages.CANT_REPORT_NOT_EXIST_USER.getMessage());
    }
}

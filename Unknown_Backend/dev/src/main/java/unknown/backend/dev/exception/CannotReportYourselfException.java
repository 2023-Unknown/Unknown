package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.ReportExceptionMessages;

public class CannotReportYourselfException extends IllegalArgumentException{
    public CannotReportYourselfException() {
        super(ReportExceptionMessages.CANT_REPORT_YOURSELF.getMessage());
    }
}

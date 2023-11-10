package unknown.backend.dev.exception;

import unknown.backend.dev.exception.constant.ReportExceptionMessages;

public class CannotReportTheSamePersonWithinADayException extends IllegalArgumentException{
    public CannotReportTheSamePersonWithinADayException() {
        super(ReportExceptionMessages.CANT_REPORT_THE_SAME_PERSON_WITHIN_A_DAY.getMessage());
    }
}

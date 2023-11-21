package unknown.backend.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unknown.backend.dev.domain.Report;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportRequest;
import unknown.backend.dev.exception.CannotReportTheSamePersonWithinADayException;
import unknown.backend.dev.exception.CannotReportYourselfException;
import unknown.backend.dev.repository.ReportRepository;

import java.time.LocalDate;
import java.util.List;
/*
    * 신고 관련 서비스
    * TODO: 몇번 이상 신고된 유저는 자동으로 비활성화
    * TODO:
 */
@Service
@Slf4j
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserService userService;
    private final Integer LAST_REPORT_DATE_INDEX = 0;
    @Autowired
    public ReportService(ReportRepository reportRepository, UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }
    public void saveReport(Report report) {
        userService.reportUser(report.getReportedEmail());
        reportRepository.save(report);
    }
    public boolean reportUser(ReportRequest reportRequest) {
        User reporter = userService.findByEmail(reportRequest.getReporterEmail());
        User reported = userService.findByEmail(reportRequest.getReportedEmail());
        if(reporter.getEmail().equals(reported.getEmail())){
            throw new CannotReportYourselfException();
        }

        List<Report> reports =  reportRepository.findByReporterEmailOrderByReportDateDesc(reporter.getEmail());
        if(reports.isEmpty()){
            Report report = Report.builder()
                    .reporterEmail(reporter.getEmail())
                    .reportedEmail(reported.getEmail())
                    .reason(reportRequest.getReason())
                    .build();

            saveReport(report);
            return true;
        }
        LocalDate lastReportDate = reports.get(LAST_REPORT_DATE_INDEX).getReportDate();
        if (lastReportDate.isAfter(LocalDate.now().minusDays(1))) {
            throw new CannotReportTheSamePersonWithinADayException();
        }
        reported.setReportCount(reported.getReportCount() + 1);

        // Reporter Entity 생성
        Report report = Report.builder()
                .reporterEmail(reporter.getEmail())
                .reportedEmail(reported.getEmail())
                .reason(reportRequest.getReason())
                .build();
        saveReport(report);
        return true;
    }
}

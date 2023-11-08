package unknown.backend.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unknown.backend.dev.domain.Report;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportDTO;
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
        reportRepository.save(report);
    }
    public boolean reportUser(ReportDTO reportDTO) {
        // 신고자
        User reporter = userService.findByEmail(reportDTO.getReporterEmail());

        // 피신고자
        User reported = userService.findByEmail(reportDTO.getReportedEmail());

        // 신고자와 피신고자가 같은지 확인
        if(reporter.getEmail().equals(reported.getEmail())){
            throw new CannotReportYourselfException();
        }

        // Reporter의 동일인물 최근 신고 날짜가 1일 이내인지 확인
        // 0번 index에 가장 최근에 신고한 날짜가 들어있음
        List<Report> reports =  reportRepository.findByReporterOrderByReportDateDesc(reporter.getUsername());
        LocalDate lastReportDate = reports.get(LAST_REPORT_DATE_INDEX).getReportDate();

        // 1일 이내에 신고한 경우
        if (lastReportDate.isAfter(LocalDate.now().minusDays(1)))
            throw new CannotReportTheSamePersonWithinADayException();

        reported.setReportCount(reported.getReportCount() + 1);

        // Reporter Entity 생성
        Report report = Report.builder()
                .reporter(reporter.getUsername())
                .reported(reported.getUsername())
                .reason(reportDTO.getReason())
                .build();

        // 저장
        saveReport(report);
        return true;
    }
}

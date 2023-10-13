package unknown.backend.dev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unknown.backend.dev.domain.Report;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportDTO;
import unknown.backend.dev.exception.UserNotFoundException;
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
    @Autowired
    private ReportRepository reportRepository;

    private UserService userService;

    public ReportService(ReportRepository reportRepository, UserService userService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
    }
    public void saveReport(Report report) {
        reportRepository.save(report);
    }
    public String reportUser(ReportDTO reportDTO) {

        // 신고자
        User reporter = userService.findByMethodAndValue("name",reportDTO.getReporter());

        // 피신고자
        User reported = userService.findByMethodAndValue("name",reportDTO.getReported());

        // 신고자와 피신고자가 같은지 확인
        if(reporter.getUsername().equals(reported.getUsername())){
            throw new UserNotFoundException("자기 자신을 신고할 수 없습니다.");
        }

        // Reporter의 동일인물 최근 신고 날짜가 1일 이내인지 확인
        // 0번 index에 가장 최근에 신고한 날짜가 들어있음
        List<Report> reports =  reportRepository.findByReporterOrderByReportDateDesc(reporter.getUsername());
        LocalDate lastReportDate = reports.get(0).getReportDate();

        // 1일 이내에 신고한 경우
        if (lastReportDate.isAfter(LocalDate.now().minusDays(1))) {
            throw new UserNotFoundException("동일인물을 1일 이내에 신고할 수 없습니다.");
        }

        reported.setReportCount(reported.getReportCount() + 1);

        // Reporter Entity 생성
        Report report = Report.builder()
                .reporter(reporter.getUsername())
                .reported(reported.getUsername())
                .reason(reportDTO.getReason())
                .build();

        // 저장
        saveReport(report);
        return "신고가 완료되었습니다.";
    }
}

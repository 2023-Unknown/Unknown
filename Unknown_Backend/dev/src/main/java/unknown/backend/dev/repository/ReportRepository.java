package unknown.backend.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unknown.backend.dev.domain.Report;
import java.util.List;


@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // 신고자 유저 이름으로 신고 내역 찾기
    List<Report> findByReporterEmailOrderByReportDateDesc(String reporterEmail);



}

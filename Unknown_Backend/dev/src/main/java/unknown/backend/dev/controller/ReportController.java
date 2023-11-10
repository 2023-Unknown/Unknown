package unknown.backend.dev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportDTO;
import unknown.backend.dev.exception.NotAllowedAccessException;
import unknown.backend.dev.exception.UserNotFoundException;
import unknown.backend.dev.service.ReportService;
import unknown.backend.dev.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
@Api(tags={"ReportAPI"})
@Slf4j
public class ReportController {
    private UserService userService;
    private ReportService reportService;

    @Autowired
    public ReportController(UserService userService, ReportService reportService){
        this.userService = userService;
        this.reportService = reportService;
    }
    @PostMapping("/")
    @ApiOperation(value="유저 신고",notes="유저를 신고합니다.")
    @ApiImplicitParam(name = "reportDTO", value = "신고 정보")
    public boolean reportUser(@RequestBody ReportDTO reportDTO) {
        try{
            User user = userService.findByEmail(reportDTO.getReporterEmail());
            log.info("신고자: " + user.getUsername());
            if(user.isActive()){
                log.info("[DEBUG] RestController: 40Line");
                return reportService.reportUser(reportDTO);
            }
            return false;
        }catch(UserNotFoundException | NotAllowedAccessException e){
            log.info(e.getMessage());
            return false;
        }
    }
}

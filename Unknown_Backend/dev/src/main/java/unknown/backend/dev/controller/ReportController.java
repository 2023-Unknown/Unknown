package unknown.backend.dev.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportDTO;
import unknown.backend.dev.service.ReportService;
import unknown.backend.dev.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
@Api(tags={"ReportAPI"})
public class ReportController {
    private UserService userService;
    private ReportService reportService;

    @Autowired
    public ReportController(UserService userService, ReportService reportService){
        this.userService = userService;
        this.reportService = reportService;
    }
    @PostMapping("/{username}")
    @ApiOperation(value="유저 신고",notes="유저를 신고합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "신고할 유저의 이름", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "reportDTO", value = "신고할 유저의 정보", required = true, dataType = "ReportDTO", paramType = "body")
    })
    public String reportUser(@PathVariable String username, @RequestBody ReportDTO reportDTO) {

        User user = userService.findByMethodAndValue("name", username);
        if(user.isActive()){
            return reportService.reportUser(reportDTO);
        }
        return reportDTO.getReported();
    }
}

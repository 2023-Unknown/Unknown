package unknown.backend.dev.controller;

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
public class ReportController {
    @Autowired
    private UserService userService;
    @Autowired
    private ReportService reportService;

    @PostMapping("/{username}")
    public String reportUser(@PathVariable String username, @RequestBody ReportDTO reportDTO) {

        User user = userService.findByMethodAndValue("name", username);
        if(user.isActive()){
            return reportService.reportUser(reportDTO);
        }
        return reportDTO.getReported();
    }
}

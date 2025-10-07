package ThinkDesk.Application.Controllers;

import ThinkDesk.Domain.Services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricsController {

    @Autowired
    private MetricsService metricsService;

    @GetMapping("/team/{teamId}")
    public ResponseEntity<Map<String, Object>> getTeamMetrics(@PathVariable Long teamId) {
        return ResponseEntity.ok(metricsService.getTeamMetrics(teamId));
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<Map<String, Object>> getEmployeeMetrics(@PathVariable Long employeeId) {
        return ResponseEntity.ok(metricsService.getEmployeeMetrics(employeeId));
    }
}

package dijam.c_box_be.databasecheck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    private final DatabaseHealthService databaseHealthService;

    public HealthCheckController(DatabaseHealthService databaseHealthService) {
        this.databaseHealthService = databaseHealthService;
    }

    @GetMapping("/db-health")
    public ResponseEntity<Map<String, Object>> checkDatabaseHealth() {
        Map<String, Object> response = new HashMap<>();
        boolean isConnected = databaseHealthService.isDatabaseConnected();
        response.put("status", isConnected ? "UP" : "DOWN");
        response.put("database", isConnected ? "Connected" : "Not Connected");
        response.put("databaseUrl", databaseHealthService.getDatabaseUrl()); // 연결된 DB URL 추가
        return ResponseEntity.ok(response);
    }
}

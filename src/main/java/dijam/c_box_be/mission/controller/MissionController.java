package dijam.c_box_be.mission.controller;

import dijam.c_box_be.mission.dto.*;
import dijam.c_box_be.mission.service.MissionService;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missions")
@CrossOrigin(origins = "http://10.0.2.2:8080")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public List<MissionResponseDto> getAllMissions() {
        return missionService.getAllMissions();
    }

    @PostMapping
    public ResponseEntity<MissionResponseDto> createMission(@RequestBody MissionRequestDto dto) {
        return ResponseEntity.ok(missionService.createMission(dto));
    }

    @GetMapping("/{mission}")
    public ResponseEntity<MissionResponseDto> getMissionDetail(@PathVariable Long mission) {
        return ResponseEntity.ok(missionService.getMissionById(mission));
    }
}



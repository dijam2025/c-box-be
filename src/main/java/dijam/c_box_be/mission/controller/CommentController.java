package dijam.c_box_be.mission.controller;

import dijam.c_box_be.mission.dto.*;
import dijam.c_box_be.mission.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto dto) {
        return ResponseEntity.ok(commentService.createComment(dto));
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<List<CommentResponseDto>> getComments(@PathVariable Long missionId) {
        return ResponseEntity.ok(commentService.getCommentsByMission(missionId));
    }
}

